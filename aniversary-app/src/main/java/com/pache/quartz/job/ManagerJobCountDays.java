package com.pache.quartz.job;

import com.pache.exception.JobManagerException;
import com.pache.quartz.job.schedule.CountDaysSchedule;
import com.pache.resource.exception.ApplicationResponseError;
import com.pache.utils.ManagerControls;

//TODO use injection 
public class ManagerJobCountDays {

	private ManagerJobCountDays() {	}

	public static void jobCountDays(ManagerControls control) throws ApplicationResponseError {
		switch (control) {
		case START:
			try {
				CountDaysSchedule.start();
			} catch (JobManagerException e) {
				throw new ApplicationResponseError(e.getMessage());
			}
			break;
		case STOP:
			try {
				CountDaysSchedule.stop();
			} catch (JobManagerException e) {
				throw new ApplicationResponseError(e.getMessage());
			}
			break;
		case RESUME:
			try {
				CountDaysSchedule.resume();
			} catch (JobManagerException e) {
				throw new ApplicationResponseError(e.getMessage());
			}
			break;
		case PAUSE:
			try {
				CountDaysSchedule.pause();
			} catch (JobManagerException e) {
				throw new ApplicationResponseError(e.getMessage());
			}
			break;
		default:
			break;
		}
	}
}
