package com.cgc.dao;

import com.cgc.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledDao {

    /**
     * 插入购买明细
     * @param seckillId 商品编号
     * @param userPhone 手机号
     * @return
     */
    int insertSuccessKilled(@Param("seckillId") Integer seckillId, @Param("userPhone") long userPhone);

    /**
     * 查找购买信息
     * @param seckillId 商品编号
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") Integer seckillId, @Param("userPhone") long userPhone);
}
