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
 * Class responsible for verify if is the anniversary and send email
 * 
 * @author lpache
 */
public class AnniversaryCount {

	private static Logger logger = LoggerFactory.getLogger(AnniversaryCount.class);

	private AnniversaryCount() {
		throw new InstantiationError("Utility class, do not instantiate!!!");
	}

	public static void sendMailToAnniversary() {
		logger.info("INIT:{} ", AnniversaryCount.class);
		for (Person item : PersonDAO.getAll()) {
			int diff = DayCountUtil.getDiffInDays(item.getInitialDate().plusYears(1).getMillis(), DateTime.now().getMillis());
			//TODO REFACTOR TO WORK EVERY YEAR NOT ONLY ONE YEAR
			if (diff == 0) {
				SendMailUtil.sendMailToAniversary(item.getEmail(), item.getName());
			}
			logger.debug("Days {}: {}", item.getName(), diff);
		}
	}

}
