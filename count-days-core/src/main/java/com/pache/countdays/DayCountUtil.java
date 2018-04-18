package com.pache.countdays;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class responsible for calculate number of days between two dates 
 * @author lpache
 */
public class DayCountUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DayCountUtil.class);
	public static final DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");

	private DayCountUtil() {
		throw new InstantiationError("Utility class, do not instantiate!!!");
	}

	protected static int getDiffInDays(long startTime, long timeNow) {
		logger.debug("Count days between {} and {}", startTime, timeNow);
		Days days = Days.daysBetween(new DateTime(startTime), new DateTime(timeNow));
		return days.getDays();
	}
}
