package com.cgc.dto;

import com.cgc.entity.SuccessKilled;
import com.cgc.enums.SeckillStateEnum;

/**
 * 封装秒杀后的结果
 */
public class SeckillExecution {

    private Integer seckillId;
    //表示结果
    private int state;
    //结果的内容表示
    private String stateInfo;
    //秒杀成功对象
    private SuccessKilled successKilled;

    /*
     * 成功
     * 返回所有数据内容
     */
    public SeckillExecution(Integer seckillId, SeckillStateEnum stateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successKilled = successKilled;
    }
    /*
     * 失败
     * 返回失败原因
     */
    public SeckillExecution(Integer seckillId, SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }

    public Integer getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Integer seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}
