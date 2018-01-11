
package com.pay.national.agent.portal.service.common.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.national.agent.common.jedis.bean.Dictionary;
import com.pay.national.agent.common.jedis.bean.DictionaryType;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.DictionaryLog;
import com.pay.national.agent.model.enums.DictionaryOperationType;
import com.pay.national.agent.portal.dao.common.DictionaryLogMapper;
import com.pay.national.agent.portal.dao.common.DictionaryMapper;
import com.pay.national.agent.portal.service.common.DictService;

/**
 * @ClassName:  DictServiceImpl
 * @Description:公共字典
 * @author: xiaodi.fu
 * @date:   2017年9月25日 上午9:46:57
 *
 */
@Service("dictService")
public class DictServiceImpl implements DictService {

	@Resource
	private DictionaryMapper dictionaryMapper;

	@Resource
	private DictionaryLogMapper dictionaryLogMapper;

	@Override
	public List<Dictionary> getAllDictById(String id) {
		return this.dictionaryMapper.getAllDictById(id);
	}

	public List<DictionaryType> getAllDict(DictionaryType type) {
		return this.dictionaryMapper.getAllDict(type);
	}

	@Override
	public Dictionary findByDictTypeIdAndDictId(String dictTypeId, String dictId) {
		return this.dictionaryMapper.findByDictTypeIdAndDictId(dictTypeId, dictId);
	}

	@Override
	public List<DictionaryType> findAllDictionaryTypeList(
			Page<List<DictionaryType>> page, Map<String, Object> queryParams) {
		return this.dictionaryMapper.findAllDictionaryTypeInfo(page, queryParams);
	}

	@Override
	public List<Dictionary> findAllDictionaryList(Page<List<Dictionary>> page,
			Map<String, Object> queryParams) {
		return this.dictionaryMapper.findAllDictionaryInfo(page, queryParams);
	}

	@Override
	public DictionaryType findDictionaryTypeByDictTypeId(String dictTypeId) {
		return this.findDictionaryTypeByDictTypeId(dictTypeId);
	}

	@Override
	public void createDictionaryType(DictionaryType dictionaryType) {
		Map<String,Object> insertParams = new HashMap<String,Object>();
		insertParams.put("code", dictionaryType.getCode());
		insertParams.put("name",dictionaryType.getName());
		insertParams.put("remark", dictionaryType.getRemark());

		this.dictionaryMapper.insertDictType(insertParams);

		//记录日志
		DictionaryLog dictionaryLog = new DictionaryLog();
		dictionaryLog.setOptimistic(0);
		dictionaryLog.setDictId(dictionaryType.getCode());
		dictionaryLog.setType(DictionaryOperationType.DICTIONARYTYPE.toString());
		dictionaryLog.setOperate(DictionaryOperationType.INSERT.toString());
		dictionaryLog.setCreateTime(new Date());

		this.dictionaryLogMapper.insert(dictionaryLog);

	}

	@Override
	public void createDictionary(Dictionary dictionary) {
		this.dictionaryMapper.insertDictionary(dictionary);
		//记录日志
		DictionaryLog dictionaryLog = new DictionaryLog();
		dictionaryLog.setDictId(dictionary.getId().toString());
		dictionaryLog.setOptimistic(0);
		dictionaryLog.setType(DictionaryOperationType.DICTIONARY.toString());
		dictionaryLog.setOperate(DictionaryOperationType.INSERT.toString());
		dictionaryLog.setCreateTime(new Date());
		this.dictionaryLogMapper.insert(dictionaryLog);
	}

	@Override
	public void updateDictionary(Dictionary dictionary) {
		this.dictionaryMapper.updateDictionary(dictionary);

		//记录日志
		DictionaryLog dictionaryLog = new DictionaryLog();
		dictionaryLog.setOptimistic(0);
		dictionaryLog.setDictId(dictionary.getId().toString());
		dictionaryLog.setType(DictionaryOperationType.DICTIONARY.toString());
		dictionaryLog.setOperate(DictionaryOperationType.UPDATE.toString());
		dictionaryLog.setCreateTime(new Date());
		this.dictionaryLogMapper.insert(dictionaryLog);
	}

	@Override
	public void updateDictionaryType(DictionaryType dictionaryType) {
		this.dictionaryMapper.updateDictionaryType(dictionaryType);

		//记录日志
		DictionaryLog dictionaryLog = new DictionaryLog();
		dictionaryLog.setOptimistic(0);
		dictionaryLog.setDictId(dictionaryType.getCode().toString());
		dictionaryLog.setType(DictionaryOperationType.DICTIONARYTYPE.toString());
		dictionaryLog.setOperate(DictionaryOperationType.UPDATE.toString());
		dictionaryLog.setCreateTime(new Date());
		this.dictionaryLogMapper.insert(dictionaryLog);
	}

}
