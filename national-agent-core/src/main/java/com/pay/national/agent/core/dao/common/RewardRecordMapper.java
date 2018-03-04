package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.beans.results.DayBussRewardGatherBean;
import com.pay.national.agent.model.entity.RewardRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RewardRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RewardRecord record);

    int insertSelective(RewardRecord record);

    RewardRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RewardRecord record);

    int updateByPrimaryKey(RewardRecord record);

    /**
     *
     * @param userNo
     * @param queryDate
     * @param parentBusinessCode
     * @return
     */
    List<DayBussRewardGatherBean> bussGatherOfDay(@Param("userNo") String userNo, @Param("queryDate") Date queryDate, @Param("parentBusinessCode") String parentBusinessCode);

    List<DayBussRewardGatherBean> bussGatherOfDetail(@Param("lowerUserNo") String userNo, @Param("queryDate") Date queryDate, @Param("parentBusinessCode") String parentBusinessCode);
}