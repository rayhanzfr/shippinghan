package com.rayhan.shippinghan.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtComponent {
	private String secretKey = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMOPQRSTUVWXYZ!@3251652145";
	private SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
	
	public String generateToken(Long userId,String username) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("userId", userId);
		claims.put("usernames", username);
		String token = Jwts.builder()
				.signWith(key)
				.setClaims(claims)
				.setExpiration(new Date(new Date().getTime()+5184000))
				.compact();
		return token;
	}
	public Claims parseClaims(String token)throws Exception {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
}
