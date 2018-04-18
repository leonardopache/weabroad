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
 * Class responsible for verify if is the anniversary and send email
 * 
 * @author lpache
 */
public class AnniversaryCount {

	private static Logger logger = LoggerFactory.getLogger(AnniversaryCount.class);

	private AnniversaryCount() {
		throw new InstantiationError("Utility class, do not instantiate!!!");
	}

	//TODO MODULE COUTNDAYS MUST HAVE ONLY DAY CALCULATIONS... it's a function of business
	public static void sendMailToAnniversary() {
		logger.info("INIT:{} ", AnniversaryCount.class);
		for (Person item : PersonDAO.getAll()) {
			if (item.getInitialDate().plusYears(1).compareTo(DateTime.now()) < 0) {
				SendMailUtil.sendMailToAniversary(item.getEmail());
			}
			logger.debug("Days {}: {}", item.getName(), DayCountUtil.getDiffInDays(item.getInitialDate().getMillis(), DateTime.now().getMillis()));
		}
	}

}
