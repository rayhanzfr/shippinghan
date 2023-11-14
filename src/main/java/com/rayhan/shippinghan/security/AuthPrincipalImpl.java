package com.rayhan.shippinghan.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthPrincipalImpl implements AuthPrincipal{
	@Override
	public Authentication getAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
