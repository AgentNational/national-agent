package com.pay.national.agent.core.service.wx.gate.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pay.national.agent.common.constants.PayConstants;
import com.pay.national.agent.common.constants.WeiXinConstant;
import com.pay.national.agent.common.exception.NationalAgentException;
import com.pay.national.agent.common.utils.HttpClientUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.dao.wx.AccessTokenManagerMapper;
import com.pay.national.agent.core.dao.wx.ApiTicketManagerMapper;
import com.pay.national.agent.core.service.wx.gate.WxService;
import com.pay.national.agent.model.entity.AccessTokenManager;
import com.pay.national.agent.model.entity.ApiTicketManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("wxService")
public class WxServiceImpl implements WxService {
    @Resource
    private AccessTokenManagerMapper accessTokenManagerMapper;




    @Override
    public  String getAccessToken(String appId, String appsecret) {
        LogUtil.info("geta服务getAccessToken请求参数 appid:{},appsecret:{}", appId, appsecret);
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=").append("client_credential").append("&appid=").append(appId).append("&secret=")
                .append(appsecret);
        String content = HttpClientUtil.sendGet(WeiXinConstant.tokenUrl, sb.toString());
        LogUtil.info("获取AccessToken 返回结果content:{}", content);
        JSONObject obj = JSON.parseObject(content);
        String accessToken = (String) obj.get("access_token");
        LogUtil.info("获取AccessToken 返回结果accessToken:{}", accessToken);
        return accessToken;
    }

    /**
     * 获取有效的微信接口访问凭证
     * 由于获取操作存在 检查在运行策略 为保证操作的原子性，需要添加同步锁。
     *
     * @param appId
     * @param appsecret
     * @return
     */
    @Override
    public synchronized String getEffectAccessToken(String appId, String appsecret) {
        //查询数据库是否有生效的accessToken
        AccessTokenManager accessTokenManager = accessTokenManagerMapper.findAccessTokenByTime(new Date());
        if (accessTokenManager == null) {//accessToken已经失效
            accessTokenManager = new AccessTokenManager();
            String accessToken = getAccessToken(appId, appsecret
            );
            accessTokenManager.setAccessToken(accessToken);
            accessTokenManager.setCreateTime(new Date());
            accessTokenManager.setEffectTime(new Date());
            accessTokenManager.setExpireTime(new Date(System.currentTimeMillis()+60*60*1000));//过期时间往后推一个小时
            accessTokenManager.setOptimistic(0);
            accessTokenManager.setStatus("ENABLE");
            accessTokenManagerMapper.insert(accessTokenManager);
        }
        return accessTokenManager.getAccessToken();
    }
    @Override
    public  String createQRCode(String accessToken,String content){
        LogUtil.info("geta服务createQrcode请求参数 accessToken:{},sence:{}", accessToken, content);
        StringBuilder sb = new StringBuilder(WeiXinConstant.qrcodeUrl);
        sb.append(accessToken);
        String result = HttpClientUtil.sendPost(sb.toString(), content);
        LogUtil.info("geta服务createQrcode 返回结果:{}", result);
        return result;
    }

    @Override
    public Map<String, String> createEnterPrisePayment(String respXml) throws Exception {

        LogUtil.info("geta服务createEnterPrisePayment 请求:{}", respXml);

        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        LogUtil.info("geta服务createEnterPrisePayment 证书地址:{}",
                WxServiceImpl.class.getResource("/").getPath() + "apiclient_cert.p12");

        FileInputStream instream = new FileInputStream(
                new File(WxServiceImpl.class.getResource("/").getPath() + "apiclient_cert.p12"));
        try {
            keyStore.load(instream, PayConstants.mchid.toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, PayConstants.mchid.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

        try {
            HttpPost httpPost = new HttpPost(WeiXinConstant.transfersUrl);
            StringEntity reqEntity = new StringEntity(respXml, "utf-8");
            // 设置类型
            reqEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            LogUtil.info("----响应：{}", JSON.toJSONString(response));
            try {
                HttpEntity entity = response.getEntity();
                LogUtil.info("-----:{}", JSON.toJSONString(entity));
                if (entity != null) {
                    // 从request中取得输入流
                    InputStream inputStream = entity.getContent();
                    // 读取输入流
                    SAXReader reader = new SAXReader();
                    Document document = reader.read(inputStream);
                    // 得到xml根元素
                    Element root = document.getRootElement();
                    // 得到根元素的所有子节点
                    @SuppressWarnings("unchecked")
                    List<Element> elementList = root.elements();
                    LogUtil.info("---map:{}", JSON.toJSONString(map));
                    // 遍历所有子节点
                    for (Element e : elementList)
                        map.put(e.getName(), e.getText());
                    // 释放资源
                    inputStream.close();
                    inputStream = null;
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }

        return map;

    }

    @Override
    public String createWxPayOrder(String outputStr) {

        LogUtil.info("createPay:outputStr:{}",outputStr);
        try {
            URL url = new URL(WeiXinConstant.payOrderUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            LogUtil.info("========{}",buffer.toString());
            return buffer.toString();
        } catch (ConnectException ce) {
            System.out.println("连接超时：{}"+ ce);
        } catch (Exception e) {
            System.out.println("https请求异常：{}"+ e);
        }
        return null;

    }

    @Override
    public String getWxPayOrder(String xml) {
        LogUtil.info("geta服务getWxPayOrder请求参数 xml:{}", xml);
        String result = HttpClientUtil.sendPost(WeiXinConstant.queryPayOrderUrl, xml);
        LogUtil.info("geta服务getWxPayOrder 返回结果:{}", result);
        return result;
    }

    @Override
    public String getApiTicket(String accessToken) {
        LogUtil.info("geta服务getApiTicket请求参数 accessToken:{}", accessToken);
        StringBuilder sb = new StringBuilder();
        sb.append("access_token=");
        sb.append(accessToken);
        sb.append("&type=");
        sb.append("jsapi");
        String content = HttpClientUtil.sendGet(WeiXinConstant.jsApiTicketUrl, sb.toString());
        LogUtil.info("geta服务getApiTicket 返回结果:{}", content);
        return content;
    }

    @Resource
    private ApiTicketManagerMapper apiTicketManagerMapper;
    /**
     * @return
     */
    @Override
    public  String getEffectApiTicket() {
        //查询数据库，查看当前是否有生效的apiTicket
        ApiTicketManager apiTicketManager = apiTicketManagerMapper.findApiTicektByTime(new Date());
        if (apiTicketManager == null) {//accessToken已经失效
            apiTicketManager = new ApiTicketManager();
            //获取accessToken
            String accessToken = getEffectAccessToken(WeiXinConstant.APP_ID, WeiXinConstant.APP_SECRET);
            //通过accessToken查询apiTicket
            String apiTicketJson = getApiTicket(accessToken);
            String apiTicket = null;
            if (!StringUtils.isEmpty(apiTicketJson)) {
                JSONObject parseObject = JSON.parseObject(apiTicketJson);
                if (parseObject.containsKey("errmsg")) {
                    String errmsg = (String) parseObject.get("errmsg");
                    if (errmsg.equals("ok")) {
                        String ticket = (String) parseObject.get("ticket");
                        apiTicket = ticket;
                    }
                }
            }
            if(apiTicket == null){
                throw new NationalAgentException("0001","apiTicket 获取失败");
            }
            apiTicketManager.setApiTicket(apiTicket);
            apiTicketManager.setCreateTime(new Date());
            apiTicketManager.setEffectTime(new Date());
            apiTicketManager.setExpireTime(new Date(System.currentTimeMillis()+60*60*1000));//过期时间往后推一个小时
            apiTicketManager.setOptimistic(0);
            apiTicketManager.setStatus("ENABLE");
            apiTicketManagerMapper.insert(apiTicketManager);
        }
        return apiTicketManager.getApiTicket();
    }
}
