package com.trainingapps.cropdeal.userMicroService.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trainingapps.cropdeal.userMicroService.MyUserDetails;
import com.trainingapps.cropdeal.userMicroService.models.MailInfo;
import com.trainingapps.cropdeal.userMicroService.models.User;
import com.trainingapps.cropdeal.userMicroService.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	public MailInfo getMailInfo(User user) {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setEmail(user.getEmail());
		mailInfo.setId(user.getUserId());
		mailInfo.setName(user.getFullName());
		mailInfo.setRole(user.getRoles());
		return mailInfo;
	}
	
	@Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return user.map(MyUserDetails::new).get();
    }
	
	public User byEmail(String email) {
		return userRepository.findByEmail(email).get();
	}
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = ((UserDetails) principal).getUsername();
//		Optional<User> user=userRepository.findByEmail(email);
		return userRepository.findByEmail(email).get();
	}
	
	// getting all books record by using the method findAll() of CrudRepository
	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(user1 -> users.add(user1));
		return users;
	}

	// getting a specific record by using the method findById() of CrudRepository
	public User findById(int id) {
		Optional<User> user = this.userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	// saving a specific record by using the method save() of CrudRepository
	public User saveUser(User user) {
		boolean checkUser = userRepository.findByEmail(user.getEmail()).isPresent();

		if (checkUser) {
			throw new IllegalStateException("User with Email already exist");
		}
		if (user.getFullName().equals("") || user.getPassword().equals("") || user.getEmail().equals("") || user.getRoles().equals("")) {
			throw new IllegalStateException("No field can be empty");
		}
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepository.save(user);
		return user;
	}

	// deleting a specific record by using the method deleteById() of CrudRepository
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	// updating a record
	public User updateUser(int userId, User userDetails) {

		User user = userRepository.findById(userId).get();

		user.setFullName(userDetails.getFullName());
		user.setEmail(userDetails.getEmail());
		String encodedPassword = bCryptPasswordEncoder.encode(userDetails.getPassword());
		user.setPassword(encodedPassword);
		user.setActive(userDetails.isActive());
		user.setRoles(userDetails.getRoles());
		userRepository.save(user);
		return user;
	}

	public ByteArrayInputStream loadExcel() {
		List<User> users = userRepository.findAll();
		ByteArrayInputStream in = ExcelHelper.usersToExcel(users);
		return in;
	}
}
