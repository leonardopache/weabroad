/**
 * 
 */
package com.pache.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

	private static final String EMAIL_PROPERTIES_FILE = "email.properties";
	private static final String TLS_PROPERTIES_FILE = "tls.properties";
	private static final String SSL_PROPERTIES_FILE = "ssl.properties";

//	private static final String MAIL_SMTP_PORT = "mail.smtp.port";
//	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
//	private static final String MAIL_SMTP_HOST = "mail.smtp.host";
//	private static final String MAIL_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
//	private static final String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
//	private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";

	public static final String EMAIL_USER = "email.user";
	public static final String EMAIL_PASS = "email.pass";

	private static Properties email = new Properties();
	private Properties tls = new Properties();
	private Properties ssl = new Properties();

	private LoadPropertiesUtils() {
		logger.info("Start load mail properties...");
		getEmailProperties();
		getTLS();
		getSSL();
	}

	public static synchronized LoadPropertiesUtils getInstance() throws IOException {
		if (instance == null) {
			instance = new LoadPropertiesUtils();
		}
		return instance;
	}

	public String getValue(String proper) {
		return email.getProperty(proper);
	}
	
	public Properties getSsl() {
		return ssl;
	}
	
	public Properties getTls() {
		return tls;
	}

	private void getEmailProperties() {
		try {
			InputStream stream = loadFileProperties(EMAIL_PROPERTIES_FILE);
			email.load(stream);
		} catch (Exception e) {
			logger.error("Error to  load properties IN {} - ERRO {}",EMAIL_PROPERTIES_FILE, e.getMessage());
		}
	}
	
	private void getTLS() {
		try {
			InputStream stream = loadFileProperties(TLS_PROPERTIES_FILE);
			tls.load(stream);
		} catch (Exception e) {
			logger.error("Error to  load properties IN {} - ERRO {}",TLS_PROPERTIES_FILE, e.getMessage());
		}
	}

	private void getSSL() {
		try {
			InputStream stream = loadFileProperties(SSL_PROPERTIES_FILE);
			ssl.load(stream);
		} catch (Exception e) {
			logger.error("Error to  load properties IN {} - ERRO {}",SSL_PROPERTIES_FILE, e.getMessage());
		}
	}
	
	private InputStream loadFileProperties(String propertiesFile) throws URISyntaxException, IOException {
		Path path = Paths.get(getClass().getClassLoader().getResource(propertiesFile).toURI());
		logger.info("Config location: " + path.toString());
		return Files.newInputStream(path);
	}
}
