package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.JwtUtil;
import com.example.demo.base.BaseResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.service.AuthUserDetailService;

@RestController
public class AuthenticationController {
    private AuthenticationManager authenticationManager;

    private AuthUserDetailService authUserDetailService;

    
    private JwtUtil jwtUtil;
    
    @Autowired
    AuthenticationController(AuthenticationManager authenticationManager,AuthUserDetailService authUserDetailService,JwtUtil jwtUtil ){
    	this.authenticationManager = authenticationManager;
    	this.authUserDetailService = authUserDetailService;
    	this.jwtUtil = jwtUtil;
    }
    
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> generateToken(@RequestBody UserDto userDto) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = authUserDetailService.loadUserByUsername(userDto.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<BaseResponse>(new BaseResponse("Bearer " + jwt, "Success"), HttpStatus.OK);
    }
}
