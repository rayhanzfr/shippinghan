package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.constant.Message;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayhan.shippinghan.dao.BranchDao;
import com.rayhan.shippinghan.dao.ProfileDao;
import com.rayhan.shippinghan.dao.UserDao;
import com.rayhan.shippinghan.dto.InsertResDataDto;
import com.rayhan.shippinghan.dto.UpdateResDataDto;
import com.rayhan.shippinghan.dto.profile.DeleteProfileResDto;
import com.rayhan.shippinghan.dto.profile.GetAllProfileResDto;
import com.rayhan.shippinghan.dto.profile.GetByProfileIdResDto;
import com.rayhan.shippinghan.dto.profile.GetProfileByUserIdResDto;
import com.rayhan.shippinghan.dto.profile.GetProfileDataDto;
import com.rayhan.shippinghan.dto.profile.InsertProfileReqDto;
import com.rayhan.shippinghan.dto.profile.InsertProfileResDto;
import com.rayhan.shippinghan.dto.profile.UpdateProfileReqDto;
import com.rayhan.shippinghan.dto.profile.UpdateProfileResDto;
import com.rayhan.shippinghan.model.Branch;
import com.rayhan.shippinghan.model.Profile;
import com.rayhan.shippinghan.model.Users;

@Service
public class ProfileServiceImpl extends BaseServiceImpl implements ProfileService {
	private ProfileDao profileDao;
	private UserDao userDao;
	private BranchDao branchDao;

	@Autowired
	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	@Override
	public Profile login(String username, String pass) throws Exception {
		profileDao.getByUsernameAndPass(username, pass);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAll() throws Exception {
		GetAllProfileResDto getAllProfileResDto = new GetAllProfileResDto();
		List<GetProfileDataDto> listGetProfileDataDto = new ArrayList<>();
		List<Profile> listProfile = profileDao.getAll();
		listProfile.forEach(r -> {
			GetProfileDataDto getProfileDataDto = new GetProfileDataDto();
			getProfileDataDto.setId(r.getId());
			getProfileDataDto.setUserId(r.getUsers().getId());
			getProfileDataDto.setBranchId(r.getBranch().getId());
			getProfileDataDto.setCode(r.getProfileCode());
			getProfileDataDto.setFirstNames(r.getFirstNames());
			getProfileDataDto.setLastNames(r.getLastNames());
			getProfileDataDto.setPhoneNumber(r.getPhoneNumber());
			getProfileDataDto.setCreatedBy(r.getCreatedBy());
			getProfileDataDto.setCreatedDate(r.getCreatedDate());
			getProfileDataDto.setUpdateBy(r.getUpdateBy());
			getProfileDataDto.setUpdateDate(r.getUpdateDate());
			getProfileDataDto.setIsActive(r.getIsActive());
			getProfileDataDto.setVersion(r.getVersion());
			listGetProfileDataDto.add(getProfileDataDto);
		});
		getAllProfileResDto.setData(listGetProfileDataDto);
		return (T) getAllProfileResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Long id) throws Exception {
		GetByProfileIdResDto getByProfileIdResDto = new GetByProfileIdResDto();
		GetProfileDataDto getProfileDataDto = new GetProfileDataDto();
		Profile profile = profileDao.getById(id);
		getProfileDataDto.setId(profile.getId());
		getProfileDataDto.setUserId(profile.getUsers().getId());
		getProfileDataDto.setBranchId(profile.getBranch().getId());
		getProfileDataDto.setCode(profile.getProfileCode());
		getProfileDataDto.setFirstNames(profile.getFirstNames());
		getProfileDataDto.setLastNames(profile.getLastNames());
		getProfileDataDto.setCreatedBy(profile.getCreatedBy());
		getProfileDataDto.setCreatedDate(profile.getCreatedDate());
		getProfileDataDto.setUpdateBy(profile.getUpdateBy());
		getProfileDataDto.setUpdateDate(profile.getUpdateDate());
		getProfileDataDto.setIsActive(profile.getIsActive());
		getProfileDataDto.setVersion(profile.getVersion());
		getByProfileIdResDto.setData(getProfileDataDto);
		return (T) getByProfileIdResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T insert(R data) throws Exception {
		InsertProfileResDto insertProfileResDto = new InsertProfileResDto();
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		InsertProfileReqDto insertProfileReqDto = (InsertProfileReqDto) data;
		Profile profile = new Profile();
		Users users = userDao.getById(insertProfileReqDto.getUserId());
		Branch branch = branchDao.getById(insertProfileReqDto.getBranchId());
		profile.setUsers(users);
		profile.setBranch(branch);
		profile.setProfileCode(insertProfileReqDto.getCode());
		profile.setFirstNames(insertProfileReqDto.getFirstNames());
		profile.setLastNames(insertProfileReqDto.getLastNames());
		profile.setPhoneNumber(insertProfileReqDto.getPhoneNumber());
		profile.setCreatedBy(getIdAuth());
		profile.setIsActive(insertProfileReqDto.getIsActive());
		insertResDataDto.setId(profileDao.insert(profile).getId());
		insertProfileResDto.setData(insertResDataDto);
		insertProfileResDto.setMessage(Message.SUCCESS.getNames());
		return (T) insertProfileResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T update(R data) throws Exception {
		UpdateProfileResDto updateProfileResDto = new UpdateProfileResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		UpdateProfileReqDto updateProfileReqDto = (UpdateProfileReqDto) data;
		Profile profile = profileDao.getById(updateProfileReqDto.getId());
		Users users = userDao.getById(updateProfileReqDto.getUserId());
		Branch branch = branchDao.getById(updateProfileReqDto.getBranchId());
		profile.setUsers(users);
		profile.setBranch(branch);
		profile.setFirstNames(updateProfileReqDto.getFirstNames());
		profile.setLastNames(updateProfileReqDto.getLastNames());
		profile.setPhoneNumber(updateProfileReqDto.getPhoneNumber());
		profile.setUpdateBy(updateProfileReqDto.getUpdateBy());
		profile.setIsActive(updateProfileReqDto.getIsActive());
		profile.setVersion(updateProfileReqDto.getVersion());
		updateResDataDto.setVersion(profileDao.update(profile).getVersion());
		updateProfileResDto.setData(updateResDataDto);
		updateProfileResDto.setMessage(Message.SUCCESS.getNames());
		return (T) updateProfileResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T> T deleteById(Long id) throws Exception {
		DeleteProfileResDto deleteProfileResDto = new DeleteProfileResDto();
		profileDao.deleteById(id);
		deleteProfileResDto.setMessage(Message.SUCCESS.getNames());
		return (T) deleteProfileResDto;
	}
	public GetProfileByUserIdResDto getProfileByUsersId(Long usersId)throws Exception{
		GetProfileByUserIdResDto getProfileByUserIdResDto = new GetProfileByUserIdResDto();
		GetProfileDataDto getProfileDataDto = new GetProfileDataDto();
		Profile profile = profileDao.getByUsersId(usersId);
		getProfileDataDto.setId(profile.getId());
		getProfileDataDto.setUserId(profile.getUsers().getId());
		getProfileDataDto.setBranchId(profile.getBranch().getId());
		getProfileDataDto.setCode(profile.getProfileCode());
		getProfileDataDto.setFirstNames(profile.getFirstNames());
		getProfileDataDto.setLastNames(profile.getLastNames());
		getProfileDataDto.setCreatedBy(profile.getCreatedBy());
		getProfileDataDto.setCreatedDate(profile.getCreatedDate());
		getProfileDataDto.setUpdateBy(profile.getUpdateBy());
		getProfileDataDto.setUpdateDate(profile.getUpdateDate());
		getProfileDataDto.setIsActive(profile.getIsActive());
		getProfileDataDto.setVersion(profile.getVersion());
		getProfileByUserIdResDto.setData(getProfileDataDto);
		return getProfileByUserIdResDto;
	}
}
