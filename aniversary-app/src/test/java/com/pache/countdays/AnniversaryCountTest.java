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
import com.pache.service.AnniversaryCount;
import com.pache.utils.SendMailUtil;

/**
 * Create test cases to one day before, the day of anniversary and one day after
 * 
 * @author lpache
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { PersonDAO.class, SendMailUtil.class })
public class AnniversaryCountTest {

	@Test
	public void testSendMailToAnniversary() {
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
			new AnniversaryCount().sendMailToAnniversary();
			fail("It shoud throw an runtime exception!!");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), message);
		} catch (EmailError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testNotSendOneDayBefore() {
		List<Person> people = new ArrayList<Person>();
		DateTime now = DateTime.now().minusYears(1).plusDays(1);
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
			new AnniversaryCount().sendMailToAnniversary();
		} catch (RuntimeException e) {
			fail("It not shoud throw an runtime exception!!");
		} catch (EmailError e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testNotSendOneDayAfter() {
		List<Person> people = new ArrayList<Person>();
		DateTime now = DateTime.now().minusYears(1).minusDays(1);
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
			new AnniversaryCount().sendMailToAnniversary();
		} catch (RuntimeException e) {
			fail("It not shoud throw an runtime exception!!");
		} catch (EmailError e) {
			fail(e.getMessage());
		}
	}

}
