
package com.pay.national.agent.portal.service.common;

import java.util.List;
import java.util.Map;

import com.pay.national.agent.common.jedis.bean.Dictionary;
import com.pay.national.agent.common.jedis.bean.DictionaryType;
import com.pay.national.agent.common.persistence.Page;



/**
 * @ClassName:  DictService
 * @Description:公共字典
 * @author: xiaodi.fu
 * @date:   2017年9月25日 上午9:44:39
 *
 */
public interface DictService {
	public List<Dictionary> getAllDictById(String id);

	public List<DictionaryType> getAllDict(DictionaryType type);

	/**
	 * @Description 根据字典类型和字典id查询字典项
	 * @param dictTypeId
	 * @param dictId
	 * @return
	 * @see 需要参考的类或方法
	 */
	public Dictionary findByDictTypeIdAndDictId(String dictTypeId, String dictId);

	public List<DictionaryType> findAllDictionaryTypeList(
			Page<List<DictionaryType>> page, Map<String, Object> queryParams);

	public List<Dictionary> findAllDictionaryList(Page<List<Dictionary>> page,
			Map<String, Object> queryParams);

	public DictionaryType findDictionaryTypeByDictTypeId(String dictTypeId);

	public void createDictionaryType(DictionaryType dictionaryType);

	public void createDictionary(Dictionary dictionary);

	public void updateDictionary(Dictionary dictionary);

	public void updateDictionaryType(DictionaryType dictionaryType);

}
