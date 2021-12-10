package com.example.demo.service;

import com.example.demo.base.BaseResponse;
import com.example.demo.database.entity.User;


public interface UserService {
	public BaseResponse getAllUsers();
	public BaseResponse findById(int id);
	public BaseResponse findByName(String name);
	public BaseResponse createNewUser(User user);
	public BaseResponse deleteUser(int id);
}
