package com.trainingapps.cropdeal.userMicroService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.trainingapps.cropdeal.userMicroService.models.User;
import com.trainingapps.cropdeal.userMicroService.repository.UserRepository;
import com.trainingapps.cropdeal.userMicroService.service.UserService;

@SpringBootTest
class UserMicroServiceApplicationTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void testJpaSave(){
		User user = new User(
				"Mahesh",
				"test@gmail.com",
				"999999",
				true,
				"ROLE_FARMER"
		);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals("Mahesh",userService.saveUser(user).getFullName());
	}
	
	@Test
	public void getAll() {
		List<User> users=new ArrayList<User>();
		User user =new User("Ramesh","test@gmail.com","9991",true,"ROLE_FARMER");
		User user1 =new User("Suresh","test1@gmail.com","9992",true,"ROLE_FARMER");
		User user2 =new User("Deepesh","test2@gmail.com","9993",true,"ROLE_DEALER");
		userRepository.save(user);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.findAll().forEach(users::add);
		assertEquals(users,userService.getAllUser());
		
	}
	@Test
	public void testGetById() {
		User user =new User("Ramesh","test2@gmail.com","999",true,"ROLE_DEALER");
//		Optional<User> d1=Optional.of(user);
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		assertEquals(user.getFullName(),userService.findById(user.getUserId()).getFullName());
	}
	@Test
	public void emptyGetById() {
		User user =new User("Ramesh","test2@gmail.com","999",true,"ROLE_DEALER");
		user.setUserId(10);
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		assertEquals(null,userService.findById(20));
	}
	@Test
	public void testDeleteById() {
		User user =new User("Ramesh","test2@gmail.com","999",true,"ROLE_FARMER");
		userService.deleteUser(user.getUserId());
		verify(userRepository,times(1)).deleteById(user.getUserId());
	}
	
	@Test
	public void testUpdateDetails() {
		User user =new User("Ramesh","test2@gmail.com","999",true,"ROLE_FARMER");
		User updateUser =new User("Dharmesh","test2@gmail.com","999",true,"ROLE_DEALER");
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(updateUser.getFullName(),userService.updateUser(user.getUserId(), updateUser).getFullName());
	}
}
