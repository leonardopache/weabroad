/**
 * 
 */
package com.pache.countdays;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pache.masterdata.Person;
import com.pache.masterdata.PersonDAO;
import com.pache.utils.SendMailUtil;

/**
 * @author lpache
 */
public class RegressiveCounting {

	private static Logger logger = LoggerFactory.getLogger(AnniversaryCount.class);

	private RegressiveCounting() {
		throw new InstantiationError("Utility class, do not instantiate!!!");
	}

	//TODO MODULE COUTNDAYS MUST HAVE ONLY DAY CALCULATIONS... it's a function of business
	public static void sendMailToFiveDaysRegressive() {
		logger.info("INIT : {}", RegressiveCounting.class);
		for (Person item : PersonDAO.getAll()) {
			int diff = DayCountUtil.getDiffInDays(DateTime.now().getMillis(), item.getInitialDate().plusYears(1).getMillis());
			if (diff > 0 && diff < 5) {
				SendMailUtil.sendMailToRegressiveDays(item.getEmail(), diff, item.getName());
			}
			logger.debug("Days {}: {}", item.getName(), diff);
		}
	}

}
