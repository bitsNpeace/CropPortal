package com.cropservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
public class EmailMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailMsApplication.class, args);
	}
//	@Autowired
//	private  emailsender sender;
//
//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException {
//
//		sender.sendRegisterationEmail("prakharpathak2040@gmail.com",
//				"Congratulation !  You have successfully registered to crop portal.",
//				"Registration confirmation"
//				);
//
//	}
}


