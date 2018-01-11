
package com.pay.national.agent.portal.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.national.agent.common.jedis.bean.Dictionary;
import com.pay.national.agent.common.jedis.bean.DictionaryType;
import com.pay.national.agent.common.jedis.tags.DictUtils;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.portal.service.common.DictService;

/**
 * @ClassName:  DictionaryController
 * @Description:公共字典控制类
 * @author: xiaodi.fu
 * @date:   2017年9月25日 上午11:08:03
 *
 */
@Controller
public class DictionaryController {
	@Resource
	private DictService dictService;

	@RequestMapping(value = "dictQuery.action")
	public String initQuery() {
		return "dict/dictQuery";
	}

	/**
	 *
	 * @Description 刷新缓存
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "excuteAllDict.action", method = RequestMethod.POST)
	public @ResponseBody
	String updateAllDict() {
		DictionaryType dictType = new DictionaryType();
		List<DictionaryType> dictList = dictService.getAllDict(dictType);
		if (null != dictList && !dictList.isEmpty()) {
			for (DictionaryType dictionaryType : dictList) {
				DictUtils.setDictionaryType(dictionaryType);
				LogUtil.info("DictionType sync success , dict_type_id = {}",
						dictionaryType.getCode());
			}
		}
		return "ok";
	}

	/**
	 * @Description 查询字典类型-跳转
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "toDictTypeQuery.action")
	public String toDictTypeQuery() {
		return "dictionary/dictionaryTypeQuery";
	}
	/**
	 * @Description 添加字典类型-跳转
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "toDictionaryTypeAdd.action")
	public String toDictionaryTypeAdd() {
		return "dictionary/dictionaryTypeAdd";
	}

	/**
	 * @Description 添加字典类型
	 * @param request
	 * @param model
	 * @param dictionaryType
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "addDictionaryType.action")
	public String addDictionaryType(HttpServletRequest request, Model model,@ModelAttribute("dictionaryType") DictionaryType dictionaryType)
	{
		String dictTypeId = dictionaryType.getCode();
		this.dictService.createDictionaryType(dictionaryType);
		model.addAttribute("dictTypeId", dictTypeId);
		return "dictionary/addDictTypeSuccess";
	}

	/**
	 * @Description 添加数字字典-跳转
	 * @param request
	 * @param model
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "toDictionaryAdd.action")
	public String toDictionaryAdd(HttpServletRequest request, Model model) {
		String dictTypeId = request.getParameter("dictTypeId");
		model.addAttribute("dictTypeId", dictTypeId);
		return "dictionary/dictionaryAdd";
	}
	/**
	 * @Description 修改数据字典-跳转
	 * @param request
	 * @param model
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "toDictionaryUpdate.action")
	public String toDictionaryUpdate(HttpServletRequest request, Model model) {
		String dictTypeId = request.getParameter("dictTypeId").toString();
		String dictId = request.getParameter("dictId").toString();
		Dictionary dictionary = this.dictService.findByDictTypeIdAndDictId(dictTypeId, dictId);
		model.addAttribute("dictionary", dictionary);
		return "dictionary/dictionaryUpdate";
	}

	/**
	 * @Description 修改数据字典
	 * @param request
	 * @param model
	 * @param dictionary
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "dictionaryUpdate.action")
	public String dictionaryUpdate(HttpServletRequest request, Model model,@ModelAttribute("dictionary") Dictionary dictionary) {
		this.dictService.updateDictionary(dictionary);
		return "dictionary/dictionaryUpdateSuccess";
	}

	/**
	 * @Description 添加数据字典
	 * @param request
	 * @param model
	 * @param dictionary
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "addDictionary.action")
	public String addDictionary(HttpServletRequest request, Model model,@ModelAttribute("dictionary") Dictionary dictionary)
	{
		String dictTypeId = dictionary.getDictTypeId();
		this.dictService.createDictionary(dictionary);
		model.addAttribute("dictTypeId", dictTypeId);
		return "dictionary/addDictTypeSuccess";
	}

	/**
	 * @Description 查询字典类型
	 * @param request
	 * @param model
	 * @param queryParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "dictTypeQuery.action")
	public String dictTypeQuery(HttpServletRequest request, Model model,
			@RequestParam Map<String, Object> queryParams) {
		// 当前页
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		Page<List<DictionaryType>> page = new Page<List<DictionaryType>>();
		page.setCurrentPage(currentPage);
		// 分页实体
		List<DictionaryType> dictionaryTypeList = this.dictService.findAllDictionaryTypeList(page, queryParams);
		model.addAttribute("page", page);
		model.addAttribute("dictionaryTypeList", dictionaryTypeList);
		LogUtil.info("method dictTypeQuery dictionaryTypeList:{}",JSONUtils.toJsonString(dictionaryTypeList));
		return "dictionary/dictionaryTypeQueryResult";
	}

	/**
	 * @Description 查询数据字典明细
	 * @param request
	 * @param model
	 * @param queryParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "dictDetail.action")
	public String dictDetail(HttpServletRequest request, Model model,
			@RequestParam Map<String, Object> queryParams) {
		// 当前页
		int currentPage = request.getParameter("currentPage") == null ? 1
				: Integer.parseInt(request.getParameter("currentPage"));
		Page<List<Dictionary>> page = new Page<List<Dictionary>>();
		page.setCurrentPage(currentPage);
		// 分页实体
		List<Dictionary> dictionaryList = this.dictService.findAllDictionaryList(page, queryParams);
		model.addAttribute("page", page);
		model.addAttribute("dictionaryList", dictionaryList);
		model.addAttribute("dictTypeId",request.getParameter("dictTypeId"));
		return "dictionary/dictDetail";
	}
	/**
	 * @Description 查询字典类型明细
	 * @param request
	 * @param model
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "findDictTypeDetail.action")
	public String findDictTypeDetail(HttpServletRequest request, Model model) {
		String dictTypeId = request.getParameter("dictTypeId");
		DictionaryType dictionaryType = this.dictService.findDictionaryTypeByDictTypeId(dictTypeId);
		model.addAttribute("dictionaryType", dictionaryType);
		return "dictionary/dictionaryTypeUpdate";
	}

	/**
	 * @Description 修改字典类型
	 * @param request
	 * @param model
	 * @param dictionaryType
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "updateDictType.action")
	public String updateDictType(HttpServletRequest request, Model model,@ModelAttribute("dictionaryType") DictionaryType dictionaryType) {
		this.dictService.updateDictionaryType(dictionaryType);
		return "dictionary/dictionaryUpdateSuccess";
	}

}
