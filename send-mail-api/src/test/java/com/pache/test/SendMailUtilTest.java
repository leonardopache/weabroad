package com.pache.test;

import static org.junit.Assert.fail;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;

import com.pache.exceptions.EmailError;
import com.pache.utils.SendMailUtil;

public class SendMailUtilTest {
	
	@Before
	public void setup() throws MessagingException {
//		Transport t = mock(Transport.class);
//		doNothing().when(t).send((Message) any(Message.class));
	}
	
//	@Test
	public void testSendByTLS() {
		String destination = "leonardo@pache.eng.br";
		String subject = "Unit Test EmailAPI";
		String body = String.format("Hi %s only %d days to Aniversary!!!<br> and counting...", "unit test", 5);
		try {
			SendMailUtil.sendByTLS(destination, subject, body);
		} catch (EmailError e) {
			fail(e.getMessage());
		}
	}

//	@Test
	public void testSendBySSL() {
		String destination = "leonardo@pache.eng.br";
		String subject = "Unit Test EmailAPI";
		String body = String.format("Hi %s congratulations!!!<br> Today is your Aniversary!!!<br> Happy BDay to youu!!", "Unit Test");
		try {
			SendMailUtil.sendBySSL(destination, subject, body);
		} catch (EmailError e) {
			fail(e.getMessage());
		}
	}

}
