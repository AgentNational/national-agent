package com.pay.national.agent.portal.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LFSUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.model.entity.AppAdVertiseInfo;
import com.pay.national.agent.portal.service.common.AppAdVertiseInfoService;

@Controller
@RequestMapping("/appAdvertise")
public class AppAdVertiseInfoController {

	@Resource
	private AppAdVertiseInfoService appAdVertiseInfoService;

	/**
	 * 列表查询-跳转
	 * @return
	 */
	@RequestMapping(value = "/toAppAdvertiseQuery.action")
	public String toAppAdvertiseQuery(Model model,Map<String, Object> queryParams) {
		return "appAdvertise/advertiseQuery";
	}

	/**
	 * 列表查询
	 * @return
	 */
	@RequestMapping(value = "/appAdvertiseQuery.action")
	public String appAdvertiseQuery(HttpServletRequest request, Model model,@RequestParam Map<String, Object> queryParams){
		LogUtil.info("method appAdvertiseQuery queryParams:{}",queryParams);
		// 当前页
        int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
        Page<List<AppAdVertiseInfo>> page = new Page<List<AppAdVertiseInfo>>();
        page.setCurrentPage(currentPage);

        //列表查询
        List<AppAdVertiseInfo> advertiseList = appAdVertiseInfoService.findAllAdvertise(page, queryParams);
        model.addAttribute("page", page);
        model.addAttribute("advertiseList", advertiseList);
		return "appAdvertise/advertiseQueryResult";
	}

	/**
	 * 广告编辑-跳转
	 * @param request
	 * @param model
	 * @param queryParams
	 * @return
	 */
	@RequestMapping(value = "/toAppAdvertiseEditor.action")
	public String toAppAdvertiseEditor(HttpServletRequest request, Model model,@RequestParam Map<String, Object> queryParams){
		toAdvertiseDetail(request, queryParams, model);
		return "appAdvertise/advertiseEditor";
	}

	/**
	 * @Description  广告编辑
	 * @param productInfoFormBeans
	 * @param request
	 * @param response
	 * @param queryParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "/appAdvertiseEditor.action")
	public String appAdvertiseEditor(AppAdVertiseInfo appAdvertiseInfo,HttpServletRequest request,HttpServletResponse response,
			@RequestParam Map<String, Object> queryParams,@RequestParam(value = "file",required=false) MultipartFile file){
		LogUtil.info("method appAdvertiseEditor data:{},file is null:{}",appAdvertiseInfo,null == file?true:false);
		//广告编辑
		appAdVertiseInfoService.appAdvertiseEditor(appAdvertiseInfo,file);

		return "appAdvertise/advertiseQuery";
	}

	/**
	 * @Description  序号位置校验
	 * @param appAdvertiseFormBeans
	 * @param request
	 * @param response
	 * @param queryParams
	 * @param model
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "/checkAdvertiseIndexNo.action")
	@ResponseBody
	public String checkAdvertiseIndexNo(AppAdVertiseInfo appAdvertiseInfo,HttpServletRequest request,HttpServletResponse response,
			@RequestParam Map<String, Object> queryParams,Model model){
		return appAdVertiseInfoService.checkAdvertiseIndexNo(appAdvertiseInfo,queryParams);
	}

	/**
	 * @Description  详细信息
	 * @param request
	 * @param response
	 * @param queryParams
	 * @param model
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "/toAdvertiseDetail.action")
	public String toAdvertiseDetail(HttpServletRequest request,@RequestParam Map<String, Object> queryParams,Model model){
		String id = request.getParameter("id");
		if(StringUtils.notNull(id)){
			AppAdVertiseInfo appAdvertiseInfo = appAdVertiseInfoService.findById(id);
			model.addAttribute("appAdvertiseInfo", appAdvertiseInfo);
		}
		return "appAdvertise/advertiseDetail";
	}
}
