/**
 * 
 */
package com.pache.masterdata;

/**
 * @author lpache
 *
 */
public class Person {
	
	protected static final String JU_START = "15/05/2017";
	protected static final String LEO_START = "18/04/2017";

	private String initialDate;
	private String email;
	private String name;
	private String locale;
	
	public Person(String initialDate, String email, String name) {
		this.initialDate = initialDate;
		this.email = email;
		this.name = name;
	}

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
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


