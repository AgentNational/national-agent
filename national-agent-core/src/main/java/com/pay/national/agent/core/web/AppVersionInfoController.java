
package com.pay.national.agent.core.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.AppVersionInfoFacade;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;

/**
 * @ClassName:  APPVersionController
 * @Description:APP版本更新
 * @author: xiaodi.fu
 * @date:   2017年9月12日 上午9:59:36
 *
 */
@Controller
@RequestMapping("/appVersion")
public class AppVersionInfoController {

	@Resource
	private AppVersionInfoFacade appVersionInfoFacade;

	@RequestMapping(value = "/versionCheck",method=RequestMethod.POST)
	public @ResponseBody String versionCheck(@RequestParam String version, @RequestParam String osType,
			@RequestParam String sign,@RequestParam String deviceType){
		LogUtil.info("Method versionCheck start version:{},osType:{},deviceType:{}" ,version,osType,deviceType);
		ReturnBean<Object> returnBean = new ReturnBean<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = appVersionInfoFacade.updateProcess(version, osType);
			returnBean.setData(map);
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
			LogUtil.info("method versionCheck data={}",JSONUtils.alibabaJsonString(returnBean));
		} catch (RuntimeException runtimeException) {
			LogUtil.error("Method versionCheck error ",runtimeException.getMessage(), runtimeException);
			returnBean = new ReturnBean<Object>("1", "版本检测失败！");
		} catch (Exception e) {
			LogUtil.error("Method versionCheck error ",e.getMessage(), e);
			returnBean = new ReturnBean<Object>("1", "版本检测失败！");
		}
		return JSONUtils.alibabaJsonString(returnBean);

	}


}
