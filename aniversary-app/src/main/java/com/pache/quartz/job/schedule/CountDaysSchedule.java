package com.pache.quartz.job.schedule;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pache.exception.JobManagerException;
import com.pache.quartz.job.AniversaryJob;
import com.pache.quartz.job.RegressiveCountJob;

public class CountDaysSchedule {
	private static final String GROUP = "group1";
	private static final Logger LOG = LoggerFactory.getLogger(CountDaysSchedule.class);
	private static Scheduler schedule;

	private CountDaysSchedule() {
	}

	public static void run() throws JobManagerException {
		LOG.info("INIT:{}", CountDaysSchedule.class);

		// Trigger the job to run every year
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", GROUP)
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 * * ?")).build();
		// Trigger the job to run the last 5 days
		Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger2", GROUP)
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 9 * * ?")).build();

		JobKey jobKeyA = new JobKey("job1", GROUP);
		JobDetail jobAniversary = JobBuilder.newJob(AniversaryJob.class).withIdentity(jobKeyA).build();
		JobKey jobKeyB = new JobKey("job2", GROUP);
		JobDetail jobRegressive = JobBuilder.newJob(RegressiveCountJob.class).withIdentity(jobKeyB).build();

		try {
			schedule = new StdSchedulerFactory().getScheduler();
			schedule.start();
			schedule.scheduleJob(jobAniversary, trigger);
			schedule.scheduleJob(jobRegressive, trigger2);
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(), e);
			throw new JobManagerException(e.getMessage());
		}
	}

	public static void start() throws JobManagerException {
		try {
			schedule.start();
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

	public static void pause() throws JobManagerException {
		try {
			schedule.pauseAll();
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(), e);
			throw new JobManagerException(e.getMessage());
		}
	}

	public static void resume() throws JobManagerException {
		try {
			schedule.resumeAll();
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(), e);
			throw new JobManagerException(e.getMessage());
		}
	}
}
