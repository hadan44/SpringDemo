package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

public interface AuthService {
    static final long EXPIRATIONTIME = 864_000_00; // 1 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    
    public void addAuthentication(HttpServletResponse res, String username);
    public Authentication getAuthentication(HttpServletRequest request);
}
