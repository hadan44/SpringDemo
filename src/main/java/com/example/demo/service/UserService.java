package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.base.BaseResponse;
import com.example.demo.database.entity.User;


public interface UserService {
	public ResponseEntity<BaseResponse> getAllUsers();
	public ResponseEntity<BaseResponse> findById(int id);
	public ResponseEntity<BaseResponse> findByName(String name);
	public ResponseEntity<BaseResponse> createNewUser(User user);
	public ResponseEntity<BaseResponse> deleteUser(int id);
}
