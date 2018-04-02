/**
 * 
 */
package com.pache.utils;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pache.exceptions.EmailError;

/**
 * Util class to send an email. Configuration file in
 * src/main/java/email.properties
 * 
 * @author lpache
 *
 */
public class SendMailUtil {

	private static Logger logger = LoggerFactory.getLogger(SendMailUtil.class);
	
	private static final String FROM_EMAIL_GMAIL_COM = "from-email@gmail.com";
	private static String username;
	private static String password;
	
	static {
		try {
			username = LoadPropertiesUtils.getInstance().getValue(LoadPropertiesUtils.EMAIL_USER);
			password = LoadPropertiesUtils.getInstance().getValue(LoadPropertiesUtils.EMAIL_PASS);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private SendMailUtil() {
		throw new InstantiationError("Not Alowed!");
	}

	public static void sendByTLS(String destination, String subject, String body, String... ccDestinations)
			throws EmailError {
		logger.info("Start send mail TLS!");

		try {
			Properties props = loadProperties("TLS");
			Session session = createSession(username, password, props);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_EMAIL_GMAIL_COM));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destination));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);
			logger.info("End send mail TLS!");
		} catch (MessagingException | IOException e) {
			logger.error(e.getMessage());
			throw new EmailError(e.getMessage());
		}
	}

	public static void sendBySSL(String destination, String subject, String body, String... ccDestinations)
			throws EmailError {
		logger.info("Start send mail SSL!");
		
		try {
			Properties props = loadProperties("SSL");
			Session session = createSession(username, password, props);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_EMAIL_GMAIL_COM));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destination));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);
			logger.info("End send mail SSL!");
		} catch (MessagingException | IOException e) {
			logger.error(e.getMessage());
			throw new EmailError(e.getMessage());
		}
	}

	private static Session createSession(final String username, final String password, Properties props) {
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		return session;
	}
	
	private static Properties loadProperties(String protocol) throws EmailError, IOException {
		Properties props;
		switch (protocol) {
		case "TLS":
			props = LoadPropertiesUtils.getInstance().getTLS();
			return props;
		case "SSL":
			props = LoadPropertiesUtils.getInstance().getSSL();
			return props;

		default:
			throw new EmailError("Error - protocol undefined");
		}
	}

	public static void sendMailToAniversary(String destinatary) {
		logger.info("One year {}!!", destinatary);
	}
}
