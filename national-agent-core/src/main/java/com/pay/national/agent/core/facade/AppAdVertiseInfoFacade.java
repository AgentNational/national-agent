package com.pay.national.agent.core.facade;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.entity.AppAdVertiseInfo;
/**
 *
 * @ClassName:  AppAdVertiseInfoFade
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 上午11:43:56
 *
 */
public interface AppAdVertiseInfoFacade {
	//根据系统类型和广告类型查询
	public ReturnBean<List<AppAdVertiseInfo>> getAppAdVertises(@RequestParam Map<String, String> params);

}
