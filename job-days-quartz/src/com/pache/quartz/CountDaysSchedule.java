package com.pache.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pache.exception.JobManagerException;
import com.pache.quartz.job.CountDaysJob;

public class CountDaysSchedule {
	private static final Logger LOG = LoggerFactory.getLogger(CountDaysSchedule.class);
	private static Scheduler schedule;
	
	private CountDaysSchedule() {}

	public static void run() throws JobManagerException {
		try {
			// Trigger the job to run every year
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 * * ?")).build();

			JobDetail job = JobBuilder.newJob(CountDaysJob.class).withIdentity("job1").build();

			SchedulerFactory schFactory = new StdSchedulerFactory();
			schedule = schFactory.getScheduler();
			schedule.scheduleJob(job, trigger);
			schedule.start();

			
			SchedulerFactory schFactory2 = new StdSchedulerFactory();
			schedule = schFactory2.getScheduler();

			schedule.scheduleJob(job, trigger);
			schedule.start();
			LOG.info("Initialized Job! {}", CountDaysJob.class);
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(), e);
			throw new JobManagerException(e.getMessage());
		}
	}

	public static void stop() throws JobManagerException {
		try {
			schedule.shutdown(true);
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(), e);
			throw new JobManagerException(e.getMessage());
		}
	}
}
