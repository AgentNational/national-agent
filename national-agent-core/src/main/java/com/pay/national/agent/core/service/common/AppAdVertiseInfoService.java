package com.pay.national.agent.core.service.common;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.entity.AppAdVertiseInfo;

public interface AppAdVertiseInfoService {
	public List<AppAdVertiseInfo> getAdVertiseInfos(@RequestParam Map<String, String> params);

}
