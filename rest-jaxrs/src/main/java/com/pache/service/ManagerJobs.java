package com.pache.service;

import com.pache.exception.JobManagerException;
import com.pache.quartz.CountDaysSchedule;
import com.pache.resource.ManagerControls;
import com.pache.resource.exception.ApplicationResponseError;

//TODO use injection 
public class ManagerJobs {

	private ManagerJobs() {	}

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
