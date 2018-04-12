/**
 * 
 */
package com.pache.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pache.countdays.AnniversaryCount;

/**
 *  Class responsible for execute the function that it will be scheduled.
 * @author lpache
 */
public class CountDaysJob implements Job {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		AnniversaryCount.sendMailToAnniversary();
		AnniversaryCount.sendMailToFiveDaysRegressive();
	}

}
