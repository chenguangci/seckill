package com.cgc.entity;

import java.util.Date;

public class Seckill {
    private Integer SeckillId;
    private String seckillName;
    private Integer number;
    private Date createTime;
    private Date beginTime;
    private Date endTime;

    @Override
    public String toString() {
        return "Seckill{" +
                "SeckillId='" + SeckillId + '\'' +
                ", seckillName='" + seckillName + '\'' +
                ", number=" + number +
                ", createTime=" + createTime +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }

    public Integer getSeckillId() {
        return SeckillId;
    }

    public void setSeckillId(Integer seckillId) {
        SeckillId = seckillId;
    }

    public String getSeckillName() {
        return seckillName;
    }

    public void setSeckillName(String seckillName) {
        this.seckillName = seckillName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
