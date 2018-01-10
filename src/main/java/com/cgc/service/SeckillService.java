package com.cgc.service;
/**
 * 站在“使用者”的角度设计接口
 * 三个方面：方法的粒度，参数，返回（return 类型/异常）
 */

import com.cgc.dto.Expose;
import com.cgc.dto.SeckillExecution;
import com.cgc.entity.Seckill;
import com.cgc.exception.RepeatKillException;
import com.cgc.exception.SeckillCloseException;
import com.cgc.exception.SeckillException;

import java.util.List;

public interface SeckillService {

    /**
     * 获取秒杀内容
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 获取单条秒杀内容
     * @param id
     * @return
     */
    Seckill getSeckillById(Integer id);

    /**
     * 秒杀开启时输出秒杀地址，否则输出系统时间和秒杀时间
     * @param id
     */
    Expose exportSeckillUrl(Integer id);

    /**
     * 执行秒杀操作，md5用于检验用户秒杀是否合法
     * @param id 秒杀内容id
     * @param userPhone 用户手机号
     * @param md5 md5值
     */
    SeckillExecution executeSeckill(Integer id, long userPhone, String md5)
            throws SeckillException,RepeatKillException, SeckillCloseException;
}
