package com.cx.edu.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class UpdateEducationTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
      log.info("==============更新edu状态===============");
    }
}
