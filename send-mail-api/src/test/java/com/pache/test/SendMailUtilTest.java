package com.pache.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.pache.exceptions.EmailError;
import com.pache.utils.SendMailUtil;

public class SendMailUtilTest {
	
	@Test
	public void testSendByTLS() {
		String destination = "leonardo@pache.eng.br";
		String subject = "Unit Test EmailAPI";
		String body = ":)";
		try {
			SendMailUtil.sendByTLS(destination, subject, body);
		} catch (EmailError e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSendBySSL() {
		String destination = "leonardo@pache.eng.br";
		String subject = "Unit Test EmailAPI";
		String body = ":)";
		try {
			SendMailUtil.sendBySSL(destination, subject, body);
		} catch (EmailError e) {
			fail(e.getMessage());
		}
	}

}
