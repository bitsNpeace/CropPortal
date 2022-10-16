package com.trainingapps.cropdeal.userMicroService.models;

public class AuthenticationResponse {
	private final String jwt;
	private final User user;
	
	public User getUser() {
		return user;
	}

	public String getJwt() {
		return jwt;
	}

	public AuthenticationResponse(String jwt,User user) {
//		super();
		this.jwt = jwt;
		this.user=user;
	}
	
}
