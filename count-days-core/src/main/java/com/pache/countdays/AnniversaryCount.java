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
 * Class responsible for verify if is the anniversary and send email
 * 
 * @author lpache
 */
public class AnniversaryCount {

	private static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
	private static Logger logger = LoggerFactory.getLogger(AnniversaryCount.class);

	private AnniversaryCount() {
		throw new InstantiationError("Utility class, do not instantiate!!!");
	}

	public static void sendMailToAnniversary() {
		logger.info("INIT:{} ", AnniversaryCount.class);
		for (Person item : PersonDAO.getAll()) {
			if (formatter.parseDateTime(item.getInitialDate()).plusYears(1).compareTo(DateTime.now()) < 0) {
				SendMailUtil.sendMailToAniversary(item.getEmail());
			}
			logger.info("Days {}: {}", item.getName(), DayCount.newCountDays(
					formatter.parseDateTime(item.getInitialDate()).getMillis(), DateTime.now().getMillis()));
		}
	}

	public static void sendMailToFiveDaysRegressive() {
		logger.info("INIT : sendMailToFiveDaysRegressive ");
		for (Person item : PersonDAO.getAll()) {
			int diff = Days.daysBetween(DateTime.now(), formatter.parseDateTime(item.getInitialDate()).plusYears(1)).getDays();
			if (diff > 0 && diff < 5) {
				SendMailUtil.sendMailToAniversary(item.getEmail());
			}
			logger.info("Days {}: {}", item.getName(), DayCount.newCountDays(
					formatter.parseDateTime(item.getInitialDate()).getMillis(), DateTime.now().getMillis()));
		}
	}

}
