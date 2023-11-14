package com.rayhan.shippinghan.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rayhan.shippinghan.model.Users;

public interface UserService extends BaseMasterService,UserDetailsService{
	Users getByUsernames(String data)throws Exception;
}
