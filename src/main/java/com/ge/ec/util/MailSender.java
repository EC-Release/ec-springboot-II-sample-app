/**
 *  Copyright (c) 2021 General Electric Company. All rights reserved.
 *
 *  The copyright to the computer software herein is the property of
 *  General Electric Company. The software may be used and/or copied only
 *  with the written permission of General Electric Company or in accordance
 *  with the terms and conditions stipulated in the agreement/contract
 *  under which the software has been supplied.
 *
 *  @author Avneesh Srivastava
 * 
 */

package com.ge.ec.util;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component
public class MailSender {

	private JavaMailSenderImpl mailSender;
	
	public JavaMailSender getMailSender(){
		if(mailSender==null){
			mailSender = new JavaMailSenderImpl();
			Properties mailProperties = new Properties();
			mailProperties.put("mail.smtp.auth", false);
			mailProperties.put("mail.smtp.starttls.enable", false);
			mailSender.setJavaMailProperties(mailProperties);
			mailSender.setHost("localhost");
			mailSender.setPort(7990);
			mailSender.setProtocol("smtp");
			mailSender.setUsername("502689453");
			mailSender.setPassword("password");
		}
		return mailSender;
	}
	public MimeMessage sendMail(String to, String from, String subject, String text) throws MessagingException {    
		MimeMessage mimeMessage = getMailSender().createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		helper.setTo(to);
		helper.setFrom(from);
		helper.setSubject(subject);
		mimeMessage.setContent(text, "text/html");
		mailSender.send(mimeMessage);
		return mimeMessage;
	}
}
