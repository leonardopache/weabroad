package com.pache.countdays;

import java.util.Calendar;

import org.junit.Test;

import junit.framework.TestCase;

public class DayCountTest extends TestCase {

	@Test
	public void testCountDays() {
		Calendar beginningDay = Calendar.getInstance();
		Calendar endingDay = Calendar.getInstance();
		endingDay.add(Calendar.DAY_OF_YEAR, 30);
		
		int count = DayCount.newCountDays(beginningDay.getTimeInMillis(), endingDay.getTimeInMillis());
		assertEquals(30, count);
	}
	
}
