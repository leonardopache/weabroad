/**
 * 
 */
package com.pache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pache.exceptions.EmailError;
import com.pache.utils.SendMailUtil;

/**
 * @author lpache
 *
 */
//TODO change the name to AbastractMailMsgEditor
public abstract class AbstractComposer {
	protected static Logger logger = LoggerFactory.getLogger(AnniversaryCount.class);
	protected int diff;
	
	//TODO change the name to mailBodyMsg and mailSubjectMsg
	protected abstract String composeMsg(String name);
	protected abstract String subject();
	
	protected void send(String destinatary, String name) {
		logger.info("validate anniversary of {}!!", destinatary);
		try {
			SendMailUtil.sendBySSL(destinatary, subject(), composeMsg(name), "leonardo@pache.eng.br");
		} catch (EmailError e) {
			logger.error("Error {}!!", e.getMessage());
		}
	}

}