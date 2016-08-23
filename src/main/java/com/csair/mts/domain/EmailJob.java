package com.csair.mts.domain;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.csair.mts.domain.email.SendMail;

public class EmailJob extends AbstractJob{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO 自动生成的方法存根
		String SEND_USER = "1@localhost";
        String SEND_UNAME = "1@localhost";
        String SEND_PWD = "1";
        String VALUE_SMTP = "localhost";
       
        try {
			SendMail.sendMail(SEND_USER, SEND_UNAME, SEND_PWD, VALUE_SMTP);
		} catch (AddressException e) {
			logger.error("Email: wrong address!");
		} catch (MessagingException e) {
			logger.error("Email: wrong message!");
		}
		
	}

	
	
}
