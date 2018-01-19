package com.cgc.enums;

/**
 * 使用枚举表示常量数据
 */
public enum SeckillStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"抱歉，此商品秒杀结束"),
    REPEAT_KILL(-1,"请勿重复秒杀"),
    INNER_ERROR(-2,"出现系统异常"),
    DATA_REWRITE(-3,"数据篡改");

    private int state;
    private String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnum stateOf(int index) {
        for (SeckillStateEnum stateEnum : values()) {
            if (stateEnum.getState() == index) {
                return stateEnum;
            }
        }
        return null;
    }
}
