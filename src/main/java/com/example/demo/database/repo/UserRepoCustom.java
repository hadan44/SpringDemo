package com.example.demo.database.repo;

import java.util.List;

import com.example.demo.database.entity.User;

public interface UserRepoCustom {
	User findByName(String username);
	User findById(int userid);
	
}
