/**
 * 
 */
package com.pache.service;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pache.countdays.DayCountUtil;
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

	public static void sendMailToFiveDaysRegressive() {
		logger.info("INIT : {}", RegressiveCounting.class);
		for (Person person : PersonDAO.getAll()) {
			diff = DayCountUtil.getDiffInDays(person.getInitialDate().plusYears(1).getMillis(),
					DateTime.now().getMillis());
			// TODO REFACTOR TO WORK EVERY YEAR NOT ONLY ONE YEAR
			if (diff >= -5 && diff < 0) {
				send(person.getEmail(), person.getName());
			}
			logger.debug("Days {}: {}", item.getName(), diff);
		}
	}

}
