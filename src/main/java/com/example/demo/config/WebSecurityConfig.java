package com.example.demo.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private DataSource dataSource;
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder(BCryptVersion.$2B, 7);
		}
		
	   @Override
	   protected void configure(HttpSecurity http) throws Exception {
	       http.authorizeRequests()
	               .antMatchers("/").permitAll() // Có nghĩa là request "/" ko cần phải đc xác thực
	       			.antMatchers(HttpMethod.DELETE).permitAll()
	       			.antMatchers(HttpMethod.POST).permitAll();
	               //.antMatchers(HttpMethod.POST, "/login").permitAll(); // Request dạng POST tới "/login" luôn được phép truy cập dù là đã authenticated hay chưa
	               //.anyRequest().authenticated() // Các request còn lại đều cần được authenticated
	               //.and()
	               // Add các filter vào ứng dụng của chúng ta, thứ mà sẽ hứng các request để xử lý trước khi tới các xử lý trong controllers.
	               // Về thứ tự của các filter, các bạn tham khảo thêm tại http://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html mục 7.3 Filter Ordering
	               //.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class) 
	               //.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	   }

//	   @Override
//	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	       auth.jdbcAuthentication().dataSource(dataSource)
//	       		.usersByUsernameQuery("select username,password from users where username = ?")
//	       		.passwordEncoder(passwordEncoder());
//	   }
}
