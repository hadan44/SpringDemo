package com.example.demo.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthServiceImpl{

//	@Override
//	public void addAuthentication(HttpServletResponse res, String username) {
//		// TODO Auto-generated method stub
//		String JWT = Jwts.builder()
//                .setSubject(username)
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
//                .signWith(SignatureAlgorithm.HS512, SECRET)
//                .compact();
//        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
//	}
//
//	@Override
//	public Authentication getAuthentication(HttpServletRequest request) {
//		String token = request.getHeader(HEADER_STRING);
//        if (token != null) {
//            // parse the token.
//            String user = Jwts.parser()
//                    .setSigningKey(SECRET)
//                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
//                    .getBody()
//                    .getSubject();
//
//            return user != null ?
//                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
//                    null;
//        }
//        return null;
//	}

}
