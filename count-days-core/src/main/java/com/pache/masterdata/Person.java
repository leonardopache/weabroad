/**
 * 
 */
package com.pache.masterdata;

import org.joda.time.DateTime;

import com.pache.countdays.DayCountUtil;

/**
 * @author lpache
 *
 */
public class Person {
	
	protected static final String JU_START = "15/05/2017";
	protected static final String LEO_START = "20/04/2017";
	

	private DateTime initialDate;
	private String email;
	private String name;
	private String locale;
	
	public Person(String initialDate, String email, String name) {
		this.initialDate = DayCountUtil.formatter.parseDateTime(initialDate);
		this.email = email;
		this.name = name;
	}

	public DateTime getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(DateTime initialDate) {
		this.initialDate = initialDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}


