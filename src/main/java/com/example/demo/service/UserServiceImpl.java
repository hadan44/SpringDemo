package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	public BaseResponse getAllUsers() {
		return new BaseResponse(userRepo.findAll(), "success");
	}

	@Override
	public BaseResponse findById(int id) {
		User user = this.userRepo.findById(id);
		if(user == null) return new BaseResponse("Not found", "fail");
		
		return new BaseResponse(user, "success");
	}

	@Override
	public BaseResponse findByName(String name) {
		User user = this.userRepo.findByName(name);
		if(user == null) return new BaseResponse("Not found", "fail");
		
		return new BaseResponse(user, "success");
	}

	@Override
	public BaseResponse createNewUser(User user) {
		// TODO Auto-generated method stub
		String encryptPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptPassword);
		try {
			userRepo.save(user);
		} catch(DataIntegrityViolationException e) {
			return new BaseResponse("Username " + "'" + user.getUsername() + "'" + " already exist", "fail");
		}
		return new BaseResponse("Save user " + user.getUsername(), "success");
	}

	@Override
	public BaseResponse deleteUser(int id) {
		try {
			userRepo.deleteById(id);;
		} catch(DataIntegrityViolationException e) {
			return new BaseResponse("id " + "'" + id + "'" + " not exist", "fail");
		}
		return new BaseResponse("delete id " + id, "success");
	}

}
