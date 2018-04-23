/**
 * 
 */
package com.pache.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pache.service.AnniversaryCount;


/**
 *  Class responsible for execute the function that it will be scheduled.
 * @author lpache
 */
public class AniversaryJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		new AnniversaryCount().sendMailToAnniversary();
	}

}
