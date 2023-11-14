package com.rayhan.shippinghan.security;

import org.springframework.security.core.Authentication;

public interface AuthPrincipal {
	Authentication getAuth();
}
