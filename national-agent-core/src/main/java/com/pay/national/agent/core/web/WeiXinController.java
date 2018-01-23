package com.pay.national.agent.core.web;

import com.pay.national.agent.common.bean.wx.TextMessage;
import com.pay.national.agent.common.constants.WeiXinConstant;
import com.pay.national.agent.common.utils.DigestUtils;
import com.pay.national.agent.common.utils.WxMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value = "/wxNotice" , method = RequestMethod.POST)
	public @ResponseBody
    void wxNoticePost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.info("wfsdjbgfkjgnfdk===============");
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
				textMessage.setContent("欢迎关注我的公众号。哈哈哈哈哈");
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
					textMessage.setContent("欢迎关注我的公众号。11111哈哈哈哈哈");
					textMessage.setCreateTime(System.currentTimeMillis());
					String message = WxMessageUtil.textMessageToXml(textMessage);
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
	
	
}
