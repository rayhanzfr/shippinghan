package com.rayhan.shippinghan.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.rayhan.shippinghan.security.jwt.JwtComponent;

import io.jsonwebtoken.Claims;

public class AuthoFilter extends BasicAuthenticationFilter {
	private JwtComponent jwtComponent;

	public AuthoFilter(AuthenticationManager authenticationManager,JwtComponent jwtComponent) {
		super(authenticationManager);
		this.jwtComponent = jwtComponent;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		if (header == null || header.isEmpty() || !header.startsWith("Bearer")) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}
		Long id=null;
		try {
			String bodyToken = header.replaceFirst("Bearer ", "");
			Claims claims = jwtComponent.parseClaims(bodyToken);
			id = Long.valueOf(claims.get("userId").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Authentication auth = new UsernamePasswordAuthenticationToken(id, null, new ArrayList<>());
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(request, response);
	}
	

}
