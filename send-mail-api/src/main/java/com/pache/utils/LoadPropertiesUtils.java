/**
 * 
 */
package com.pache.utils;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lpache
 *
 */
public class LoadPropertiesUtils {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static LoadPropertiesUtils instance;

	private static final String EMAIL_PROPERTIES_FILE = "/email.properties";

	private static final String MAIL_SMTP_PORT = "mail.smtp.port";
	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	private static final String MAIL_SMTP_HOST = "mail.smtp.host";
	private static final String MAIL_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
	private static final String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
	private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	
	public static final String EMAIL_USER = "email.user";
	public static final String EMAIL_PASS = "email.pass";
	
	private static Properties properties = new Properties();

	private LoadPropertiesUtils() {
		logger.info("Start load mail properties...");
		
		properties.put(EMAIL_USER, "user");
		properties.put(EMAIL_PASS, "pass");
	}
	
	public static synchronized LoadPropertiesUtils getInstance() throws IOException {
		if(instance == null) {
			instance = new LoadPropertiesUtils();
		}
		return instance;
	}
	
	public String getValue(String proper) {
		return properties.getProperty(proper);
	}
	
	public Properties getTLS() {
		Properties properties = new Properties();
		properties.put(MAIL_SMTP_AUTH, "true");
		properties.put(MAIL_SMTP_HOST, "smtp.gmail.com");
		properties.put(MAIL_SMTP_STARTTLS_ENABLE, "true");
		properties.put(MAIL_SMTP_PORT, "587");
		return properties;
	}
	
	public Properties getSSL() {
		Properties properties = new Properties();
		properties.put(MAIL_SMTP_AUTH, "true");
		properties.put(MAIL_SMTP_HOST, "smtp.gmail.com");
		properties.put(MAIL_SMTP_SOCKET_FACTORY_PORT, "465");
		properties.put(MAIL_SMTP_SOCKET_FACTORY_CLASS, "javax.net.ssl.SSLSocketFactory");
		properties.put(MAIL_SMTP_PORT, "465");
		return properties;
	}
}
