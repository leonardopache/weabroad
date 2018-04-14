/**
 * 
 */
package com.pache.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pache.countdays.RegressiveCounting;

/**
 * 
 * @author lpache
 */
public class RegressiveCountJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		RegressiveCounting.sendMailToFiveDaysRegressive();
	}

}
