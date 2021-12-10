package com.example.demo.dto;

import com.example.demo.database.entity.User;

public class UserDto {
	
	private String username;
	private String password;
	
	public User convertToUser() {
		User user = new User();
		user.setUsername(this.username);
		user.setPassword(this.password);
		user.setGender("Male");
		System.out.println(user.toString());
		return user;
	}
}
