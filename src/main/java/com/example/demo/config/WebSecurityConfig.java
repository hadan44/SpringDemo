package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.auth.JwtRequestFilter;
import com.example.demo.service.AuthUserDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
	    private AuthUserDetailService userDetailsService;
	
	    private JwtRequestFilter jwtFilter;
	    
	    @Autowired 
	    WebSecurityConfig(AuthUserDetailService userDetailsService,JwtRequestFilter jwtFilter ){
	    	this.userDetailsService = userDetailsService;
	    	this.jwtFilter = jwtFilter;
	    }
	    
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder(BCryptVersion.$2B, 7);
		}
	    
//	    @Bean
//	    public PasswordEncoder passwordEncoder(){
//	        return NoOpPasswordEncoder.getInstance();
//	    }
		
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService);
	    }


	    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable().authorizeRequests()
	        		.antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html/**","swagger-ui/index.html#/", "/auth").permitAll()
	        		.anyRequest().authenticated()
	                .and().exceptionHandling().and().sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and().httpBasic().disable();
	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	        http.cors();
	    }
}
