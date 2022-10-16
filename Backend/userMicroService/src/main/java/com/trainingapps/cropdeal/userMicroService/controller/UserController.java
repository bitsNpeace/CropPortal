package com.trainingapps.cropdeal.userMicroService.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.trainingapps.cropdeal.userMicroService.MyUserDetails;
import com.trainingapps.cropdeal.userMicroService.models.AuthenticationRequest;
import com.trainingapps.cropdeal.userMicroService.models.AuthenticationResponse;
//import com.trainingapps.cropdeal.userMicroService.MyUserDetailsService;
//import com.trainingapps.cropdeal.userMicroService.models.AuthenticationResponse;
import com.trainingapps.cropdeal.userMicroService.models.Crop;
import com.trainingapps.cropdeal.userMicroService.models.MailInfo;
import com.trainingapps.cropdeal.userMicroService.models.User;
import com.trainingapps.cropdeal.userMicroService.service.UserService;
import com.trainingapps.cropdeal.userMicroService.util.jwtUtil;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="*" ,allowedHeaders="*")
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
//	@Autowired
//	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private jwtUtil jwtUtilToken;
	@Autowired 
	private RestTemplate restTemplate;
	@Autowired
    private UserService userService;
	
//	@GetMapping("/profile")
//	private String show() {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		if (principal instanceof UserDetails) {
//		  return ((UserDetails)principal).getUsername();
//		} else {
//		  return principal.toString();
//		}
//		
//	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuth(@RequestBody AuthenticationRequest authRequest) throws Exception{
		try {
			
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
		}catch(BadCredentialsException e){
			throw new Exception("incorrect username or password",e);
		}
		final UserDetails userDetails= userService.loadUserByUsername(authRequest.getEmail());
		User user =restTemplate.postForObject("http://localhost:8082/users/check",authRequest.getEmail() ,User.class);
		final String jwt=jwtUtilToken.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt,user));
	}
	
	
    //creating post mapping that post the farmer detail in the database
    @PostMapping("/create")
    private int saveUser(@RequestBody User user)
    {
        userService.saveUser(user);
        MailInfo mailInfo=userService.getMailInfo(user);
//        restTemplate.postForObject("http://localhost:7100/email", mailInfo, void.class);
        return user.getUserId();
    }
    @PostMapping("/check")
    private User checkUser(@RequestBody String email)
    {
    	
		return userService.byEmail(email);
    }
    
    @GetMapping("/show/{id}")
    public User findById(@PathVariable int id) {
    	User response = userService.findById(id);
        return response;
    }


    //creating put mapping that updates the farmer detail
    @PutMapping("/update/{id}")
    private User updateUser(@PathVariable(value="id") int id,@RequestBody User user) {
        return userService.updateUser(id,user);
        
    }
	
	

}
