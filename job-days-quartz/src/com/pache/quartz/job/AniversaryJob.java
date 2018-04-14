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
public class AniversaryJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		AnniversaryCount.sendMailToAnniversary();
	}

}
