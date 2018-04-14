/**
 * 
 */
package com.pache.countdays;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pache.masterdata.Person;
import com.pache.masterdata.PersonDAO;
import com.pache.utils.SendMailUtil;

/**
 * @author lpache
 */
public class RegressiveCounting {

	private static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
	private static Logger logger = LoggerFactory.getLogger(AnniversaryCount.class);

	private RegressiveCounting() {
		throw new InstantiationError("Utility class, do not instantiate!!!");
	}

	public static void sendMailToFiveDaysRegressive() {
		logger.info("INIT : {}", RegressiveCounting.class);
		for (Person item : PersonDAO.getAll()) {
			int diff = Days.daysBetween(DateTime.now(), formatter.parseDateTime(item.getInitialDate()).plusYears(1)).getDays();
			if (diff > 0 && diff < 5) {
				SendMailUtil.sendMailToRegressiveDays(item.getEmail(), diff+1);
			}
			logger.debug("Days {}: {}", item.getName(), DayCount.newCountDays(
					formatter.parseDateTime(item.getInitialDate()).getMillis(), DateTime.now().getMillis()));
		}
	}

}
