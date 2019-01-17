package com.fqz.quartz.controller;

import com.fqz.quartz.model.Result;
import com.fqz.quartz.model.ResultCodeEnum;
import com.fqz.quartz.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fuqianzhong
 * @date 19/1/15
 */
@Controller
public class JobController {
    @Autowired
    private JobService jobService;

    @RequestMapping("/simple")
    @ResponseBody
    public Result triggerSimpleJob(){
        jobService.triggerSimple();
        return new Result<String>(ResultCodeEnum.SUCCESS,null);
    }
    @RequestMapping("/cron")
    @ResponseBody
    public Result triggerCronJob(){
        jobService.triggerCron();
        return new Result<String>(ResultCodeEnum.SUCCESS,null);
    }

}
