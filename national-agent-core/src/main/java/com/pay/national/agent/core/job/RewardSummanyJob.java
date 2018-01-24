package com.pay.national.agent.core.job;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pay.commons.utils.lang.DateUtils;
import com.pay.national.agent.common.utils.LogUtil;

/**
 * 奖励汇总定时任务
 * @author shuyan.qi
 * Date:2017年9月26日上午3:51:42
 */
@Component
public class RewardSummanyJob{

	public void execute() {
		Date yesterday = DateUtils.addDays(new Date(), -1);
		try {
		} catch (Exception e) {
			LogUtil.error("RewardSummanyJob yesterday:{}error:{}",yesterday,e);
		}
	}

}
