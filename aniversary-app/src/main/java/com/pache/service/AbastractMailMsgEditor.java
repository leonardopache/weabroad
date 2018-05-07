/**
 * 
 */
package com.pache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pache.exceptions.EmailError;
import com.pache.utils.SendMailUtil;

/**
 * abstract class to delegate the custom messages to subclass
 * 
 * @author lpache
 *
 */
public abstract class AbastractMailMsgEditor {
	protected static Logger logger = LoggerFactory.getLogger(AbastractMailMsgEditor.class);
	protected int diff;
	
	protected abstract String mailBodyMsg(String name);
	protected abstract String mailSubjectMsg();
	
	protected void send(String destinatary, String name) {
		logger.info("validate anniversary of {}!!", destinatary);
		try {
			SendMailUtil.sendBySSL(destinatary, mailSubjectMsg(), mailBodyMsg(name), "leonardo@pache.eng.br");
		} catch (EmailError e) {
			logger.error("Error {}!!", e.getMessage());
		}
	}

}
