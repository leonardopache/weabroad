package com.pache.service;

import com.pache.exception.JobManagerException;
import com.pache.quartz.CountDaysSchedule;
import com.pache.resource.exception.ApplicationResponseError;

//TODO use injection 
public class ManagerJobs {

	private ManagerJobs() {	}

	public static void startJobCountDays() throws ApplicationResponseError {
		try {
			CountDaysSchedule.run();
		} catch (JobManagerException e) {
			throw new ApplicationResponseError(e.getMessage());
		}
	}

	public static void stopJobCountDays() throws ApplicationResponseError {
		try {
			CountDaysSchedule.stop();
		} catch (JobManagerException e) {
			throw new ApplicationResponseError(e.getMessage());
		}
	}
}
