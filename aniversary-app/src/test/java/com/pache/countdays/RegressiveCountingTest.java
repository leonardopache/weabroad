package com.pache.countdays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.pache.exceptions.EmailError;
import com.pache.masterdata.Person;
import com.pache.masterdata.PersonDAO;
import com.pache.service.RegressiveCounting;
import com.pache.utils.SendMailUtil;

/**
 * Create test cases to six days before, five days before and the day of the
 * anniversary.
 * 
 * @author lpache
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { PersonDAO.class, SendMailUtil.class })

public class RegressiveCountingTest {

	@Test
	public void testNotSendSixDaysBefore() {
		List<Person> people = new ArrayList<Person>();
		DateTime now = DateTime.now().minusYears(1).minusDays(6);
		people.add(new Person(now.getDayOfMonth() + "/" + now.getMonthOfYear() + "/" + now.getYear(),
				"unitTest@mock.test", "Mr. Mockito"));

		// mock getAll
		PowerMockito.mockStatic(PersonDAO.class);
		PowerMockito.when(PersonDAO.getAll()).thenReturn(people);

		// mock sendMail
		PowerMockito.mockStatic(SendMailUtil.class);
		String message = "Mail sent";
		PowerMockito.doThrow(new RuntimeException(message)).when(SendMailUtil.class);

		try {
			SendMailUtil.sendBySSL(anyString(), anyString(), anyString(), anyString());
			new RegressiveCounting().sendMailToFiveDaysRegressive();
		} catch (RuntimeException e) {
			fail("It not shoud throw an runtime exception!!");
		} catch (EmailError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSendFiveDaysBefore() {
		List<Person> people = new ArrayList<Person>();
		DateTime now = DateTime.now().minusYears(1).minusDays(5);
		people.add(new Person(now.getDayOfMonth() + "/" + now.getMonthOfYear() + "/" + now.getYear(),
				"unitTest@mock.test", "Mr. Mockito"));

		// mock getAll
		PowerMockito.mockStatic(PersonDAO.class);
		PowerMockito.when(PersonDAO.getAll()).thenReturn(people);

		// mock sendMail
		PowerMockito.mockStatic(SendMailUtil.class);
		String message = "Mail sent";
		PowerMockito.doThrow(new RuntimeException(message)).when(SendMailUtil.class);

		try {
			SendMailUtil.sendBySSL(anyString(), anyString(), anyString(), anyString());
			new RegressiveCounting().sendMailToFiveDaysRegressive();
			fail("It shoud throw an runtime exception!!");
		} catch (RuntimeException e) {
			assertEquals(message, e.getMessage());
		} catch (EmailError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testNotSendTheDayOfAnniversary() {
		List<Person> people = new ArrayList<Person>();
		DateTime now = DateTime.now().minusYears(1);
		people.add(new Person(now.getDayOfMonth() + "/" + now.getMonthOfYear() + "/" + now.getYear(),
				"unitTest@mock.test", "Mr. Mockito"));

		// mock getAll
		PowerMockito.mockStatic(PersonDAO.class);
		PowerMockito.when(PersonDAO.getAll()).thenReturn(people);

		// mock sendMail
		PowerMockito.mockStatic(SendMailUtil.class);
		String message = "Mail sent";
		PowerMockito.doThrow(new RuntimeException(message)).when(SendMailUtil.class);

		try {
			SendMailUtil.sendBySSL(anyString(), anyString(), anyString(), anyString());
			new RegressiveCounting().sendMailToFiveDaysRegressive();
		} catch (RuntimeException e) {
			fail("It not shoud throw an runtime exception!!");
		} catch (EmailError e) {
			fail(e.getMessage());
		}
	}

}
