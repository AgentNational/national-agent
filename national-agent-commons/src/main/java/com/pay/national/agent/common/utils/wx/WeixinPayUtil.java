package com.pay.national.agent.common.utils.wx;

import com.pay.national.agent.common.constants.PayConstants;
import com.pay.national.agent.common.utils.DigestUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class WeixinPayUtil {


    // xml解析
    public static Map<String, String> doXMLParse(String strxml) throws Exception {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        if (null == strxml || "".equals(strxml)) {
            return null;
        }
        Map<String, String> m = new HashMap<String, String>();
        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List<Element> list = root.getChildren();
        Iterator<Element> it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List<Element> children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }
            m.put(k, v);
        }

        // 关闭流
        in.close();
        return m;
    }

    public static String getChildrenText(List<Element> children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator<Element> it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List<Element> list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }
        return sb.toString();
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    public static String createSign(SortedMap<String, String> packageParams) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String, String>> es = packageParams.entrySet();
        Iterator<Map.Entry<String, String>> it = es.iterator();
        while (it.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + PayConstants.paysign);
        String sign = DigestUtils.md5(sb.toString()).toUpperCase();
        return sign;

    }

    public static String getSign(Map<String, String> paramMap, String key) {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        List list = new ArrayList(paramMap.keySet());
        Object[] ary = list.toArray();
        Arrays.sort(ary);
        list = Arrays.asList(ary);
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i) + "=" + paramMap.get(list.get(i) + "") + "&";
        }
        str += "key=" + key;
        str = DigestUtils.md5(str).toUpperCase();

        return str;
    }

    /**
     * 处理xml请求信息
     *
     * @param request
     * @return
     */
    public static String getXmlRequest(HttpServletRequest request) {
        java.io.BufferedReader bis = null;
        String result = "";
        try {
            bis = new java.io.BufferedReader(new java.io.InputStreamReader(request.getInputStream()));
            String line = null;
            while ((line = bis.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 参数进行XML化
     *
     * @param map,sign
     * @return
     */
    public static String parseString2Xml(Map<String, String> map, String sign) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<Map.Entry<String, String>> es = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = es.iterator();
        while (iterator.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry) iterator.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            sb.append("<" + k + ">" + v + "</" + k + ">");
        }
        sb.append("<sign>" + sign + "</sign>");
        sb.append("</xml>");
        return sb.toString();
    }

}
