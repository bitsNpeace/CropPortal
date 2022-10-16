package com.trainingapps.cropdeal.dealerMicroService.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.trainingapps.cropdeal.userMicroService.models.User;

@SpringBootTest
public class ModelTest {
	
	
	@Test
	public void testSetId() {
		User user =new User("Ramesh","test2@gmail.com","999",true,"ROLE_FARMER");
		user.setUserId(35);
		assertEquals(35,user.getUserId());
	}
}
