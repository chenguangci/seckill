package com.cgc.service.impl;

import com.cgc.dao.SeckillDao;
import com.cgc.dao.SuccessKilledDao;
import com.cgc.dto.Expose;
import com.cgc.dto.SeckillExecution;
import com.cgc.entity.Seckill;
import com.cgc.entity.SuccessKilled;
import com.cgc.enums.SeckillStateEnum;
import com.cgc.exception.RepeatKillException;
import com.cgc.exception.SeckillCloseException;
import com.cgc.exception.SeckillException;
import com.cgc.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    //日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    //md5盐值，混淆md5
    private static final String salt = "MyBatisSpringSpringMVCSpringBootSpringDataSpringCloud";

    private String getMD5(Integer id) {
        String str = id+"/"+salt;
        return  DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,7);
    }

    public Seckill getSeckillById(Integer id) {
        return seckillDao.queryById(id);
    }

    public Expose exportSeckillUrl(Integer id) {
        Seckill seckill = getSeckillById(id);
        if (seckill == null) {
            return new Expose(false, id);
        }
        Date begin = seckill.getBeginTime();
        Date end = seckill.getEndTime();
        Date now = new Date();
        if (now.getTime() < begin.getTime() || now.getTime() > end.getTime()) {
            return new Expose(false, id, now.getTime(), begin.getTime(), end.getTime());
        }
        String md5 = getMD5(id);
        return new Expose(true, md5, id);
    }

    @Transactional
    /**
     * 使用注解声明事务方法的优点：
     * 1.明确标注事务方法
     * 2.保证事务方法执行时间尽可能短，不要穿插其他网络方法，如果需要就剥离到外层
     * 3.事务方法应该只有数据库操作
     * 4.不是所有方法都需要事务控制，比如只有一条修改
     */
    public SeckillExecution executeSeckill(Integer id, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || !md5.equals(getMD5(id))) {
            throw new SeckillException("seckill data rewrite");
        }
        try {
            //执行秒杀操作，减库存+购买行为
            Date now = new Date();
            int updateCount = seckillDao.reduceNumber(id, now);
            if (updateCount <= 0) {
                throw new SeckillCloseException("seckill is closed");
            } else {
                //记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(id, userPhone);
                if (insertCount <= 0) {
                    throw new RepeatKillException("seckill repeat");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, userPhone);
                    return new SeckillExecution(id, SeckillStateEnum.SUCCESS, successKilled);
                }

            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //所有编译器异常转化为运行期异常，便于spring事务回滚
            throw new SeckillException("seckill inner error : " + e.getMessage());
        }

    }
}
