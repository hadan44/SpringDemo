package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import com.example.demo.base.BaseResponse;
import com.example.demo.database.entity.User;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/user/getAll", method = RequestMethod.GET)
	@Operation(summary = "Get user by ID", description = "This can only be done by admin.", 
	security = { @SecurityRequirement(name = "bearer-key") },
	tags = { "user" })
	public ResponseEntity<BaseResponse> getAllUser() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/user/getById", method = RequestMethod.GET)
	@Operation(summary = "Find user by ID", description = "This can only be done by admin.", 
	security = { @SecurityRequirement(name = "bearer-key") },
	tags = { "user" })
	public ResponseEntity<BaseResponse> findById(@RequestParam int userId) {
		return userService.findById(userId);
	}
	
	@RequestMapping(value = "/user/getByName", method = RequestMethod.GET)
	@Operation(summary = "Find user by name", description = "This can only be done by admin.", 
	security = { @SecurityRequirement(name = "bearer-key") },
	tags = { "user" })
	public ResponseEntity<BaseResponse> findByName(@RequestParam String name) {
		return userService.findByName(name);
	}
	
	@RequestMapping(value = "/user/createNewUser", method = RequestMethod.POST)
	@Operation(summary = "Create new user", description = "This can only be done by admin.", 
	security = { @SecurityRequirement(name = "bearer-key") },
	tags = { "user" })
	public ResponseEntity<BaseResponse> creatNewUser(@RequestBody UserDto userDto) {
		User user = userDto.convertToUser();
		return userService.createNewUser(user);
	}
	
	@RequestMapping(value = "/user/deleteById", method = RequestMethod.DELETE)
	@Operation(summary = "Delete user by ID", description = "This can only be done by admin.", 
	security = { @SecurityRequirement(name = "bearer-key") },
	tags = { "user" })
	public ResponseEntity<BaseResponse> deleteById(@RequestParam int userId) {
		return userService.deleteUser(userId);
	}
	
	
}
