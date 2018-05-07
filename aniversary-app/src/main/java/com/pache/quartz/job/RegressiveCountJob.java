/**
 * 
 */
package com.pache.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pache.service.RegressiveCounting;

/**
 * 
 * @author lpache
 */
public class RegressiveCountJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		new RegressiveCounting().sendMailToFiveDaysRegressive();
	}

}
