
package com.pay.national.agent.portal.dao.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.common.jedis.bean.Dictionary;
import com.pay.national.agent.common.jedis.bean.DictionaryType;
import com.pay.national.agent.common.persistence.Page;



/**
 * @ClassName:  DictionaryMapper
 * @Description:公共字典
 * @author: xiaodi.fu
 * @date:   2017年9月25日 上午9:22:59
 *
 */
public interface DictionaryMapper {
	/**
    *
    * 查询字典项
    *
    * @param id
    * @return
    * @see [类、类#方法、类#成员]
    */
   public List<Dictionary> getAllDictById(String id);
	 /**
     * 查询字典组
     *
     *
     * @param type
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<DictionaryType> getAllDict(DictionaryType type);

    /**
     * @Description  根据字典类型和字典id查询字典项
     * @param dictTypeId
     * @param dictId
     * @return
     * @see 需要参考的类或方法
     */
    public Dictionary findByDictTypeIdAndDictId(@Param("dictTypeId")String dictTypeId, @Param("dictId")String dictId);

	public List<DictionaryType> findAllDictionaryTypeInfo(
			@Param("page") Page<List<DictionaryType>> page, @Param("queryParams") Map<String, Object> queryParams);

	public List<Dictionary> findAllDictionaryInfo(
			@Param("page") Page<List<Dictionary>> page, @Param("queryParams") Map<String, Object> queryParams);

	public DictionaryType findDictTypeByDictTypeId(String dictTypeId);

	public void insertDictType(@Param("insertParams") Map<String,Object> insertParams);

	public void insertDictionary(Dictionary dictionary);

	public void updateDictionary(Dictionary dictionary);

	public void updateDictionaryType(DictionaryType dictionaryType);
}
