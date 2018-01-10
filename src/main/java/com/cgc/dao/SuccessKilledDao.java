package com.cgc.dao;

import com.cgc.entity.SuccessKilled;

public interface SuccessKilledDao {

    /**
     * 插入购买明细
     * @param seckillId 商品编号
     * @param userPhone 手机号
     * @return
     */
    int insertSuccessKilled(Integer seckillId, Long userPhone);

    /**
     * 查找购买信息
     * @param seckillId 商品编号
     * @return
     */
    SuccessKilled queryById(Integer seckillId);
}
