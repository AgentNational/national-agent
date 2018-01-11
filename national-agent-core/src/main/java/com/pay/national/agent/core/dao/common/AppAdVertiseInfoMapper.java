package com.pay.national.agent.core.dao.common;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.pay.national.agent.model.entity.AppAdVertiseInfo;
/**
 *
 * @ClassName:  AppAdVertiseInfoMapper
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 上午11:44:28
 *
 */
public interface AppAdVertiseInfoMapper{
	public List<AppAdVertiseInfo> getAppAdVertiseInfos(@RequestParam Map<String, String> params);

}
