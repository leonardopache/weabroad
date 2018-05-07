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

/**
 * @author lpache
 */
public class RegressiveCounting extends AbastractMailMsgEditor {

	private static Logger logger = LoggerFactory.getLogger(AnniversaryCount.class);

	public void sendMailToFiveDaysRegressive() {
		logger.info("INIT : {}", RegressiveCounting.class);
		for (Person person : PersonDAO.getAll()) {
			diff = DayCountUtil.getDiffInDays(person.getInitialDate().plusYears(1).getMillis(),
					DateTime.now().getMillis());
			// TODO REFACTOR TO WORK EVERY YEAR NOT ONLY ONE YEAR
			if (diff >= -5 && diff < 0) {
				send(person.getEmail(), person.getName());
			}
			logger.debug("Days {}: {}", person.getName(), diff);
		}
	}

	@Override
	protected String mailBodyMsg(String name) {
		return String.format("Hi %s only %d days to Aniversary!!!<br> and counting...", name, Math.abs(diff));
	}

	@Override
	protected String mailSubjectMsg() {
		return "Regressive Counting";
	}

}
