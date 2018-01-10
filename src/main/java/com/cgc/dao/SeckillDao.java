package com.cgc.dao;

import com.cgc.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId 商品编号
     * @param killTime 秒杀时间
     * @return
     */
    int reduceNumber(@Param("seckillId") Integer seckillId, @Param("killTime") Date killTime);

    /**
     * 查询单条记录
     * @param seckillId 商品编号
     * @return
     */
    Seckill queryById(Integer seckillId);

    /**
     * 查找所有信息
     * @param begin 偏移量
     * @param number 每页条数
     * @return
     */
    List<Seckill> queryAll(@Param("begin") int begin, @Param("number") int number);
}
