package com.cgc.dto;

/**
 * 暴露秒杀地址的DTO
 * DTO 非实体类，只是为了方便返回信息
 */
public class Expose {

    //是否开启
    private boolean expose;
    //加密
    private String md5;
    //id
    private Integer seckillId;
    //当前系统时间（单位毫秒）
    private long now;
    //秒杀开启时间
    private long begin;
    //秒杀结束时间
    private long end;

    public Expose(boolean expose, String md5, Integer seckillId) {
        this.expose = expose;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Expose(boolean expose, Integer seckillId, long now, long begin, long end) {
        this.expose = expose;
        this.seckillId = seckillId;
        this.now = now;
        this.begin = begin;
        this.end = end;
    }

    public Expose(boolean expose, Integer seckillId) {
        this.expose = expose;
        this.seckillId = seckillId;
    }

    @Override
    public String toString() {
        return "Expose{" +
                "expose=" + expose +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }

    public boolean isExpose() {
        return expose;
    }

    public void setExpose(boolean expose) {
        this.expose = expose;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Integer getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Integer seckillId) {
        this.seckillId = seckillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getBegin() {
        return begin;
    }

    public void setBegin(long begin) {
        this.begin = begin;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
