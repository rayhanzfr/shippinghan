package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.security.AuthPrincipal;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl {
	protected AuthPrincipal authPrincipal;
	
	@Autowired
	public void setAuthPrincipal(AuthPrincipal authPrincipal) {
		this.authPrincipal = authPrincipal;
	}
	protected Long getIdAuth() throws Exception {
		if(authPrincipal.getAuth()==null || authPrincipal.getAuth().getPrincipal()==null) {
			throw new Exception("Invalid user");
		}
		return (Long)authPrincipal.getAuth().getPrincipal();
	}
}
