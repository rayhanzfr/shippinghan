package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.dto.profile.GetProfileByUserIdResDto;
import com.rayhan.shippinghan.model.Profile;

public interface ProfileService extends BaseMasterService{
	Profile login(String username,String pass)throws Exception;
	GetProfileByUserIdResDto getProfileByUsersId(Long usersId)throws Exception;
}
