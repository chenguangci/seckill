package com.cgc.web;

import com.cgc.dto.Expose;
import com.cgc.dto.SeckillExecution;
import com.cgc.dto.SeckillResult;
import com.cgc.entity.Seckill;
import com.cgc.enums.SeckillStateEnum;
import com.cgc.exception.RepeatKillException;
import com.cgc.exception.SeckillCloseException;
import com.cgc.exception.SeckillException;
import com.cgc.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/seckill")
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(Model model, @PathVariable(value = "seckillId") Integer seckillId) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getSeckillById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/expose", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Expose> expose(@PathVariable(value = "seckillId") Integer seckillId) {
        SeckillResult<Expose> result;
        try{
            Expose expose = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Expose>(true, expose);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Expose>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{key}/execution", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable(value = "seckillId") Integer seckillId,
                                                   @PathVariable(value = "key") String key,
                                                   @CookieValue(value = "killPhone", required = false) Long userPhone) {
        if (userPhone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, key);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (RepeatKillException e1) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(false, seckillExecution);
        } catch (SeckillCloseException e2) {
          SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.END);
          return new SeckillResult<SeckillExecution>(false, seckillExecution);
        } catch (SeckillException e) {
            logger.error(e.getMessage(), e);
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(false, seckillExecution);
        }
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> getTime() {
        Date date = new Date();
        return new SeckillResult<Long>(true, date.getTime());
    }
}
