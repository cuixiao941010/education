package com.cx.edu.scheduled;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Configuration
public class QuartzConfiguration {

	@Bean
	public JobDetail updateEducationobDetail() {
		return JobBuilder.newJob(UpdateEducationTask.class).withIdentity("updateEducationTask")
			.storeDurably().build();
	}

	@Bean
	public Trigger updateEducationTrigger() {

		String cron = "0 0/1 * * * ?";
		return TriggerBuilder.newTrigger().forJob(updateEducationobDetail())
			.withIdentity("updateEducationTrigger").withSchedule(cronSchedule(cron)).build();
	}

}
