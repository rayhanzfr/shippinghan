package com.rayhan.shippinghan.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rayhan.shippinghan.dto.login.LoginReqDto;
import com.rayhan.shippinghan.dto.login.LoginResDto;
import com.rayhan.shippinghan.dto.profile.GetProfileByUserIdResDto;
import com.rayhan.shippinghan.model.Users;
import com.rayhan.shippinghan.security.jwt.JwtComponent;
import com.rayhan.shippinghan.service.ProfileService;
import com.rayhan.shippinghan.service.UserService;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private ObjectMapper objectMapper;
	private UserService userService;
	private ProfileService profileService;
	private JwtComponent jwtComponent;

	public AuthFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper, JwtComponent jwtComponent,
			UserService userService,ProfileService profileService) {
		this.authenticationManager = authenticationManager;
		this.objectMapper = objectMapper;
		this.jwtComponent = jwtComponent;
		this.userService = userService;
		this.profileService=profileService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		LoginReqDto loginReqDto = new LoginReqDto();
		try {
			loginReqDto = objectMapper.readValue(request.getInputStream(), LoginReqDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginReqDto.getUsername(), loginReqDto.getPass()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		LoginResDto loginResDto = new LoginResDto();
		try {
			Users user = userService.getByUsernames(authResult.getName());
			String token = jwtComponent.generateToken(user.getId(),user.getUsername());
			GetProfileByUserIdResDto getProfileByUserIdResDto = profileService.getProfileByUsersId(user.getId());
			loginResDto.setToken(token);
			loginResDto.setRoleCode(user.getRoles().getRoleCode());
			loginResDto.setUserId(user.getId());
			if(getProfileByUserIdResDto.getData().getId()!=null) {				
				loginResDto.setBranchId(getProfileByUserIdResDto.getData().getBranchId());
			}
			response.setContentType("application/json");
			response.getWriter().append(objectMapper.writeValueAsString(loginResDto));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		Map<String,Object> error = new HashMap<>();
		error.put("message", "Login Failed");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().append(objectMapper.writeValueAsString(error));
	}
}
