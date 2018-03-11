package com.pay.national.agent.core.web;

import com.alibaba.fastjson.JSON;
import com.pay.national.agent.common.bean.wx.TextMessage;
import com.pay.national.agent.common.bean.wx.WxJssdkConfig;
import com.pay.national.agent.common.constants.WeiXinConstant;
import com.pay.national.agent.common.utils.DigestUtils;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.common.utils.WxMessageUtil;
import com.pay.national.agent.core.service.common.UserService;
import com.pay.national.agent.core.service.wx.impl.WxJssdkConfigMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/weiXin")
public class WeiXinController {
	private static final Logger logger = LoggerFactory.getLogger(WeiXinController.class);
	
	@RequestMapping(value = "/wxNotice" , method = RequestMethod.GET)
	public @ResponseBody
    String wxNotice(HttpServletRequest request){
		logger.info("微信验证服务器地址的有效性。。。。。。。");
		// 开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带参数
		try {
			String signature = request.getParameter("signature");// 微信加密签名（token、timestamp、nonce。）
			String timestamp = request.getParameter("timestamp");// 时间戳
			String nonce = request.getParameter("nonce");// 随机数
			String echostr = request.getParameter("echostr");// 随机字符串
			logger.info("signatur:{},timestamp:{},nonce:{},echostr:{}", signature, timestamp, nonce, echostr);

			// 将token、timestamp、nonce三个参数进行字典序排序
			String[] params = new String[] {WeiXinConstant.TOKEN, timestamp, nonce };
			Arrays.sort(params);
			// 将三个参数字符串拼接成一个字符串进行加密
			String clearText = params[0] + params[1] + params[2];
			String sign = DigestUtils.getSha1Str(clearText);

			// 获得加密后的字符串可与signature对比
			if (signature.equals(sign)) {
				return echostr;
			}
		} catch (Exception e) {
			logger.error("微信验证服务器地址的有效性，服务异常:{}", e.getMessage());
			e.printStackTrace();
		}
		return "ERROR";
	}

	@Resource
	private UserService userService;

	@RequestMapping(value = "/wxNotice" , method = RequestMethod.POST)
	public @ResponseBody
    void wxNoticePost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.info("微信推送消息开始。。");
		response.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = response.getWriter();
		try {
			Map<String, String> map = WxMessageUtil.parseXml(request);
			String Content = map.get("Content");
			String ToUserName = map.get("ToUserName");
			String FromUserName = map.get("FromUserName");
			String MsgType = map.get("MsgType");
			String FuncFlag = map.get("FuncFlag");
			String Event = map.get("Event");
			System.out.println(map);
			if(WxMessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(MsgType)){
				TextMessage textMessage = new TextMessage();
				textMessage.setFromUserName(ToUserName);
				textMessage.setToUserName(FromUserName);
				textMessage.setMsgType("text");
				textMessage.setContent("欢迎关注全民代理公众号！");
				textMessage.setCreateTime(System.currentTimeMillis());
				String message = WxMessageUtil.textMessageToXml(textMessage);
				System.out.println(message);
				printWriter.print(message);
			}else if(WxMessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(MsgType)){
				if(WxMessageUtil.EVENT_TYPE_SUBSCRIBE.equals(Event)){
					TextMessage textMessage = new TextMessage();
					textMessage.setFromUserName(ToUserName);
					textMessage.setToUserName(FromUserName);
					textMessage.setMsgType("text");
					textMessage.setContent("欢迎您来到全民代理平台，我们为您准备了各种便于线上分享推广的高奖励任务，如：\n" +
							"推荐办理信用卡：30-50元/张\n" +
							"推荐免费领取快速发票码：50元/单\n" +
							"推荐免费领取信用卡刷卡工具：0.0005*刷卡交易额\n" +
							"更多业务，请点击下方“代理产品”菜单查看详情\n" +
							"\n" +
							"除此之外，您还可以推荐他人关注公众号，您将获得推荐人收益的5%作为奖励，请通过点击下方“我的海报”菜单，获取您的专属推广海报，再将海报图片发送给其他好友关注，切勿直接分享公众号给好友奥；\n" +
							"\n" +
							"所有的收益可随时提现至您的微信零钱，详情请点击下方“提现”菜单；");
					textMessage.setCreateTime(System.currentTimeMillis());
					String message = WxMessageUtil.textMessageToXml(textMessage);
					String EventKey = map.get("EventKey");
					if(StringUtils.isNotBlank(EventKey)){
						userService.register(FromUserName,EventKey);
					}else{
						userService.register(FromUserName,null);
					}
					System.out.println(message);
					printWriter.print(message);
				}else if(WxMessageUtil.EVENT_TYPE_CLICK.equals(Event)){
					String EventKey = map.get("EventKey");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			printWriter.close();
		}
	}

	@Resource
	private WxJssdkConfigMethod wxJssdkConfigMethod;

	/**
	 * @Description 获取jssdk配置
	 * @param request
	 * @return
	 * @see
	 */
	@RequestMapping(value = "/jssdkConfig", method = RequestMethod.GET)
	public @ResponseBody String wxJssdkConfig(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String currentUrl = request.getParameter("currentUrl");
		logger.info("WxController currentUrl:" + currentUrl);
		WxJssdkConfig wxJssdkConfig = wxJssdkConfigMethod.getWxJssdkConfig(currentUrl);
		logger.info("WxController wxJssdkConfig:" + JSON.toJSONString(wxJssdkConfig));
		return JSON.toJSONString(wxJssdkConfig);
	}



}
