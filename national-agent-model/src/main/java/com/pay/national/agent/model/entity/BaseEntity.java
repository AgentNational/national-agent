package com.pay.national.agent.model.entity;

import java.io.Serializable;

/**
 * 基础实体
 * Created by shuyan.qi on 2018/1/23.
 */
public class BaseEntity implements Serializable{
    private static final long serialVersionUID = -8433969938301280877L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 乐观锁
     */
    private Integer optimistic = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", optimistic=" + optimistic +
                '}';
    }
}
