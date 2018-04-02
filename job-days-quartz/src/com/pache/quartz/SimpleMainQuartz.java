/**
 * 
 */
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

import com.pache.quartz.job.CountDaysJob;

/**
 * @author lpache
 */
public class SimpleMainQuartz {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleMainQuartz.class);

	public static void main(String[] args) {
		try {
			// define the job and tie it to our HelloJob class
			JobDetail job = JobBuilder.newJob(CountDaysJob.class)
					.withIdentity("job1").build();

			// Trigger the job to run every minute
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1").withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
			
			SchedulerFactory schFactory = new StdSchedulerFactory();
			Scheduler sched = schFactory.getScheduler();

			sched.scheduleJob(job, trigger);
			sched.start();
			LOG.info("Initialized Job! {}", CountDaysJob.class);
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(), e);
		} 
	}
}
