package com.bridgelabz.spring.utility;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Autowired
	private MailSender mailSender;

	public void sendEmail(String toEmail, String subject, String activationUrl) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("kashyapmridumurchana25@gmail.com");
			msg.setTo("kashyapmridumurchana25@gmail.com");
			String message = "Please click on the link to verify \n\n" + activationUrl;
			msg.setText(message);
			msg.setSentDate(new Date());
			mailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
