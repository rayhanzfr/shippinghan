package com.rayhan.shippinghan.dao;

import com.rayhan.shippinghan.model.Users;

public interface UserDao extends BaseMasterDao<Users> {
	Users getByUsername(String usernames)throws Exception;
}
