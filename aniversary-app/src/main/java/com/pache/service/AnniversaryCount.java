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
 * Class responsible for verify if is the anniversary and send email
 * 
 * @author lpache
 */
public class AnniversaryCount extends AbastractMailMsgEditor {

	private static Logger logger = LoggerFactory.getLogger(AnniversaryCount.class);

	public void sendMailToAnniversary() {
		logger.info("INIT:{} ", AnniversaryCount.class);
		for (Person person : PersonDAO.getAll()) {
			
			if(DayCountUtil.isAniversary(person.getInitialDate(), DateTime.now())) {
				send(person.getEmail(), person.getName());
			}
			
			logger.debug("Days {}: {}", person.getName(), diff);
		}
	}

	@Override
	protected String mailBodyMsg(String name) {
		return String.format("Hi %s congratulations!!!<br> Today is your Aniversary!!!<br> Happy BDay to youu!!", name);
	}

	@Override
	protected String mailSubjectMsg() {
		return "Aniversary";
	}

}
