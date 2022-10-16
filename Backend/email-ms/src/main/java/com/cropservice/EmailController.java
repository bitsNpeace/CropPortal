package com.cropservice;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EmailController {
	@Autowired
	private emailsender sender;

	@PostMapping("/email")
	public void triggerMail(@RequestBody MailInfo mailInfo) throws MessagingException {
		String role=mailInfo.getRole().substring(5);
		
		sender.sendRegisterationEmail(mailInfo.getEmail(),
				"Congratulations "+mailInfo.getName()+" !  You have successfully registered to crop portal as a "+role+" . Your UserId is "+mailInfo.getId(), "Registration confirmation");

	}

}
