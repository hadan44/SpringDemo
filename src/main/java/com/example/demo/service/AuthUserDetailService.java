package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.database.repo.UserRepo;

import java.util.ArrayList;

@Service
public class AuthUserDetailService implements UserDetailsService {
	private UserRepo userRepo;
	
	@Autowired
	public AuthUserDetailService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	com.example.demo.database.entity.User user = this.userRepo.findByName(username);
    	
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}

