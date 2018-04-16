/**
 * 
 */
package com.pache.utils;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
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

			MimeMessage message = new MimeMessage(session);
			message.addHeader("Content-type", "text/HTML; charset=utf-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			
			message.setFrom(new InternetAddress(FROM_EMAIL_GMAIL_COM, true));
			for (String ccDestination : ccDestinations) {
				message.addRecipient(RecipientType.BCC, new InternetAddress(ccDestination, true));
			}
			message.setSubject(subject, "utf-8");
			message.setSentDate(new Date());
			
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destination));
			message.setSubject(subject);
//			message.setContent(getBodyGeneric(body), "text/html");
			message.setText(getBodyGeneric(body), "utf-8", "html");
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

			MimeMessage message = new MimeMessage(session);
			message.addHeader("Content-type", "text/HTML; charset=utf-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			
			message.setFrom(new InternetAddress(FROM_EMAIL_GMAIL_COM, true));
			for (String ccDestination : ccDestinations) {
				message.addRecipient(RecipientType.BCC, new InternetAddress(ccDestination, true));
			}
			message.setSubject(subject, "utf-8");
			message.setSentDate(new Date());
			
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destination));
			message.setSubject(subject);
//			message.setContent(getBodyGeneric(body), "text/html");
			message.setText(getBodyGeneric(body), "utf-8", "html");
			
			Transport.send(message);
			logger.info("End send mail SSL!");
		} catch (MessagingException | IOException e) {
			logger.error(e.getMessage());
			throw new EmailError(e.getMessage());
		}
	}
	
	private static String getBodyGeneric(String str) {
		return String.format("<div style=\"text-align: center;\"><b><font color=black size=\"8\">%s</font></b><br></div>", str);
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
		switch (protocol) {
		case "TLS":
			return LoadPropertiesUtils.getInstance().getTls();
		case "SSL":
			return LoadPropertiesUtils.getInstance().getSsl();
		default:
			throw new EmailError("Error - protocol undefined");
		}
	}

	public static void sendMailToAniversary(String destinatary) {
		logger.info("One year {}!!", destinatary);
		try {
			sendBySSL(destinatary, "Aniversary", "5\n and counting...", "leonardo@pache.eng.br");
		} catch (EmailError e) {
			logger.error("Error {}!!", e.getMessage());
		}
	}

	public static void sendMailToRegressiveDays(String destinatary, int diff, String name) {
		logger.info("{} year {}!!", diff,  destinatary);
		try {
			String body = String.format("Hi %s only %d days to Aniversary!!!\n and counting...",name, diff);
			sendBySSL(destinatary, "Regressive Counting", body, "leonardo@pache.eng.br");
		} catch (EmailError e) {
			logger.error("Error {}!!", e.getMessage());
		}
	}
}
