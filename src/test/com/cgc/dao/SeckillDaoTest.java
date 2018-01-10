package com.cgc.dao;

import com.cgc.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    @Resource
    private SeckillDao seckillDao;
    @Test
    public void reduceNumber() throws Exception {

    }

    @Test
    public void queryById() throws Exception {
        System.out.println(seckillDao.queryById(1000).toString());
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> list = seckillDao.queryAll(0,100);
        System.out.println("总数：+"+list.size());
        for (Seckill seckill : list){
            System.out.println(seckill.toString());
        }
    }

}