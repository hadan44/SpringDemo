package com.example.demo.dto;

import javax.validation.constraints.NotBlank;

import com.example.demo.database.entity.User;

public class UserDto {
	
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User convertToUser() {
		User user = new User();
		user.setUsername(this.username);
		user.setPassword(this.password);
		user.setGender("Male");
		System.out.println(user.toString());
		return user;
	}
}
