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
	
	public static int getDiffInDays(long startTime, long timeNow) {
		logger.debug("Count days between {} and {}", startTime, timeNow);
		Days days = Days.daysBetween(new DateTime(startTime).withTimeAtStartOfDay(), new DateTime(timeNow).withTimeAtStartOfDay());
		return days.getDays();
	}

	public static boolean isAniversary(DateTime date1, DateTime date2) {
		date1 = date1.withYear(date2.getYear());
		int diff = DayCountUtil.getDiffInDays(date1.getMillis(),
				date2.getMillis());
		if (diff == 0) {
			return true;
		}
		return false;
	}
}
