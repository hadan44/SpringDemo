package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.base.BaseResponse;
import com.example.demo.database.entity.User;
import com.example.demo.database.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	private UserRepo userRepo;
	private PasswordEncoder passwordEncoder;
	
	
	
	@Autowired
	public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public ResponseEntity<BaseResponse> getAllUsers() {
		return new ResponseEntity<BaseResponse>(new BaseResponse(userRepo.findAll(), "success"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BaseResponse> findById(int id) {
		User user = this.userRepo.findById(id);
		if(user == null) return new ResponseEntity<BaseResponse>(new BaseResponse("Not found", "fail"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<BaseResponse>(new BaseResponse(user, "success"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BaseResponse> findByName(String name) {
		User user = this.userRepo.findByName(name);
		if(user == null) return new ResponseEntity<BaseResponse>(new BaseResponse("Not found", "fail"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<BaseResponse>(new BaseResponse(user, "success"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BaseResponse> createNewUser(User user) {
		// TODO Auto-generated method stub
		String encryptPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptPassword);
		try {
			userRepo.save(user);
		} catch(DataIntegrityViolationException e) {
			return new ResponseEntity<BaseResponse>(
					new BaseResponse("Username " + "'" + user.getUsername() + "'" + " already exist", "fail")
					, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<BaseResponse>(new BaseResponse("Save user " + user.getUsername(), "success"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BaseResponse> deleteUser(int id) {
		try {
			userRepo.deleteById(id);;
		} catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<BaseResponse>(new BaseResponse("Id " + "'" + id + "'" + " not exist", "fail"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<BaseResponse>(new BaseResponse("Delete id " + id, "success"), HttpStatus.OK);
	}

}
