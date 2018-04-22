package com.pache.conf;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pache.exception.JobManagerException;
import com.pache.quartz.job.schedule.CountDaysSchedule;

@WebListener
public class MyJobServletListener implements ServletContextListener {

	private static final Logger LOG = LoggerFactory.getLogger(MyJobServletListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			CountDaysSchedule.run();
			LOG.info(">>>>>>> ServletContextEvent initialized!!{}", sce);
		} catch (JobManagerException e) {
			LOG.error(e.getMessage());
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			CountDaysSchedule.stop();
			LOG.info("<<<<<<< ServletContextEvent destroyed!!{}", sce);
		} catch (JobManagerException e) {
			LOG.error(e.getMessage());
		}
	}
}
