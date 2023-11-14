package com.rayhan.shippinghan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rayhan.shippinghan.security.jwt.JwtComponent;
import com.rayhan.shippinghan.service.ProfileService;
import com.rayhan.shippinghan.service.UserService;

//@Profile("Test")
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter{
	
	private UserService userService;
	
	private ProfileService profileService;
	
	private ObjectMapper objectMapper;

	private JwtComponent jwtComponent;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Autowired
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	@Autowired
	public void setJwtComponent(JwtComponent jwtComponent) {
		this.jwtComponent = jwtComponent;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
		http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated();
		http.addFilter(new AuthFilter(super.authenticationManager(), objectMapper, jwtComponent, userService,profileService));
		http.addFilter(new AuthoFilter(super.authenticationManager(), jwtComponent));
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder); 
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST,"/users");
		web.ignoring()
			.antMatchers(HttpMethod.GET,"/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**");
	}
}
