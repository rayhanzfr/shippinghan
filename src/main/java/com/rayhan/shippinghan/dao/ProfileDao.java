package com.rayhan.shippinghan.dao;

import com.rayhan.shippinghan.model.Profile;

public interface ProfileDao extends BaseMasterDao<Profile>{
	Profile getByUsernameAndPass(String username,String pass)throws Exception;
	Profile getByUsersId(Long usersId)throws Exception;
}
