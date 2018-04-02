package com.pache.countdays;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class responsible for calculate number of days between two dates 
 * @author lpache
 */
public class DayCount {
	
	private static Logger logger = LoggerFactory.getLogger(DayCount.class);

	private DayCount() {
		throw new InstantiationError("Utility class, do not instantiate!!!");
	}

	protected static int newCountDays(long startTime, long timeNow) {
		logger.debug("Count days between {} and {}", startTime, timeNow);
		Days days = Days.daysBetween(new DateTime(startTime), new DateTime(timeNow));
		return days.getDays();
	}
}
