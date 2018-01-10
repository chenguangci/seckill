package com.cgc.service;

import com.cgc.dto.Expose;
import com.cgc.dto.SeckillExecution;
import com.cgc.entity.Seckill;
import com.cgc.exception.SeckillException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml","classpath:spring/spring-dao.xml"})
public class SeckillServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckills = seckillService.getSeckillList();
        logger.info("list:{}", seckills);
    }

    @Test
    public void getSeckillById() throws Exception {
        Seckill seckill = seckillService.getSeckillById(1000);
        logger.info("seckill:{}",seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        Expose expose = seckillService.exportSeckillUrl(1013);
        logger.info("expose:{}",expose);
        /*
         * Expose{expose=true, md5='cfa4ea86d333336a8a19666efc02e46e', seckillId=1000, now=0, begin=0, end=0}
         */
    }

    @Test
    public void executeSeckill() throws Exception {
        SeckillExecution seckill = seckillService.executeSeckill(1013, 17876253432L, "cfa4ea86d333336a8a19666efc02e46e");
        logger.info("seckill:{}",seckill);
    }

}