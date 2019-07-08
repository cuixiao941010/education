package com.cx.edu.scheduled.api;

import com.cx.edu.exception.ResponseData;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.quartz.CronScheduleBuilder.cronSchedule;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private Scheduler scheduler;

    @GetMapping("execute")
    public ResponseData executeActivation(@RequestParam String jobKey) throws SchedulerException {
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(JobKey.jobKey(jobKey))
                .usingJobData("manually", true)
                .build();
        scheduler.scheduleJob(trigger);
        return new ResponseData();
    }

    @GetMapping("cron")
    public ResponseData dynamicUpdateCron(@RequestParam String triggerKey, @RequestParam String cron) throws SchedulerException {
        TriggerKey key = TriggerKey.triggerKey(triggerKey);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(key);
        CronTrigger newTrigger = trigger.getTriggerBuilder().withSchedule(cronSchedule(cron)).build();
        scheduler.rescheduleJob(key, newTrigger);
        return new ResponseData();
    }
}
