package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.base.BaseResponse;
import com.example.demo.database.entity.User;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/user/getAll", method = RequestMethod.GET)
	public BaseResponse getAllUser() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/user/getById", method = RequestMethod.GET)
	public BaseResponse findById(@RequestParam int userId) {
		return userService.findById(userId);
	}
	
	@RequestMapping(value = "/user/getByName", method = RequestMethod.GET)
	public BaseResponse findByName(@RequestParam String name) {
		return userService.findByName(name);
	}
	
	@RequestMapping(value = "/user/createNewUser", method = RequestMethod.POST)
	public BaseResponse creatNewUser(@RequestBody UserDto userDto) {
		User user = userDto.convertToUser();
		return userService.createNewUser(user);
	}
	
	@RequestMapping(value = "/user/deleteById", method = RequestMethod.DELETE)
	public BaseResponse deleteById(@RequestParam int userId) {
		return userService.deleteUser(userId);
	}
	
	
}
