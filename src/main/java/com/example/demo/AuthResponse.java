package com.example.demo;

public class AuthResponse {
	
	private String token;
	private String username;

	public AuthResponse(String token,String username) {
		super();
		this.token = token;
		this.username = username;
	}

	public String gettoken() {
		return token;
	}

	public void settoken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
