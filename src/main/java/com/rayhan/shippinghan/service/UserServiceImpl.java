package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.constant.Message;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rayhan.shippinghan.dao.RolesDao;
import com.rayhan.shippinghan.dao.UserDao;
import com.rayhan.shippinghan.dto.InsertResDataDto;
import com.rayhan.shippinghan.dto.UpdateResDataDto;
import com.rayhan.shippinghan.dto.users.DeleteUsersResDto;
import com.rayhan.shippinghan.dto.users.GetAllUsersResDto;
import com.rayhan.shippinghan.dto.users.GetByUsersIdResDto;
import com.rayhan.shippinghan.dto.users.GetUsersDataDto;
import com.rayhan.shippinghan.dto.users.InsertUsersReqDto;
import com.rayhan.shippinghan.dto.users.InsertUsersResDto;
import com.rayhan.shippinghan.dto.users.UpdateUsersReqDto;
import com.rayhan.shippinghan.dto.users.UpdateUsersResDto;
import com.rayhan.shippinghan.model.Roles;
import com.rayhan.shippinghan.model.Users;

@Service
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private RolesDao rolesDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public void setUsersDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAll() throws Exception {
		GetAllUsersResDto getAllUsersResDto = new GetAllUsersResDto();
		List<GetUsersDataDto> listGetUsersDataDto = new ArrayList<>();
		List<Users> listUsers = userDao.getAll();
		listUsers.forEach(r -> {
			GetUsersDataDto getUsersDataDto = new GetUsersDataDto();
			getUsersDataDto.setUsername(r.getUsername());
			getUsersDataDto.setEmail(r.getEmail());
			getUsersDataDto.setCreatedBy(r.getId());
			getUsersDataDto.setCreatedDate(r.getCreatedDate());
			getUsersDataDto.setUpdateBy(r.getUpdateBy());
			getUsersDataDto.setUpdateDate(r.getUpdateDate());
			getUsersDataDto.setIsActive(r.getIsActive());
			getUsersDataDto.setVersion(r.getVersion());
			listGetUsersDataDto.add(getUsersDataDto);
		});
		getAllUsersResDto.setData(listGetUsersDataDto);
		return (T) getAllUsersResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Long id) throws Exception {
		GetByUsersIdResDto getByUsersIdResDto = new GetByUsersIdResDto();
		GetUsersDataDto getUsersDataDto = new GetUsersDataDto();
		Users user = new Users();
		user = userDao.getById(id);
		getUsersDataDto.setUsername(user.getUsername());
		getUsersDataDto.setEmail(user.getEmail());
		getUsersDataDto.setCreatedBy(user.getId());
		getUsersDataDto.setCreatedDate(user.getCreatedDate());
		getUsersDataDto.setUpdateBy(user.getUpdateBy());
		getUsersDataDto.setUpdateDate(user.getUpdateDate());
		getUsersDataDto.setIsActive(user.getIsActive());
		getUsersDataDto.setVersion(user.getVersion());
		getByUsersIdResDto.setData(getUsersDataDto);
		return (T) getByUsersIdResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T insert(R data) throws Exception {
		InsertUsersResDto insertUsersResDto = new InsertUsersResDto();
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		InsertUsersReqDto insertUsersReqDto = (InsertUsersReqDto) data;
		Users user = new Users();
		Roles roles = rolesDao.getById(insertUsersReqDto.getRoleId());
		user.setRoles(roles);
		user.setUsername(insertUsersReqDto.getUsername());
		user.setPass(bCryptPasswordEncoder.encode(insertUsersReqDto.getPass()));
		user.setEmail(insertUsersReqDto.getEmail());
		user.setCreatedBy(insertUsersReqDto.getCreateBy());
		user.setIsActive(insertUsersReqDto.getIsActive());
		Long id = userDao.insert(user).getId();
		insertResDataDto.setId(id);
		insertUsersResDto.setData(insertResDataDto);
		insertUsersResDto.setMessage(Message.SUCCESS.getNames());
		return (T) insertUsersResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T update(R data) throws Exception {
		UpdateUsersResDto updateUsersResDto = new UpdateUsersResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		UpdateUsersReqDto updateUsersReqDto = (UpdateUsersReqDto) data;
		Roles roles = rolesDao.getById(updateUsersReqDto.getRoleId());
		Users users = userDao.getById(updateUsersReqDto.getId());
		users.setPass(updateUsersReqDto.getPass());
		users.setEmail(updateUsersReqDto.getEmail());
		users.setRoles(roles);
		users.setUpdateBy(updateUsersReqDto.getUpdateBy());
		users.setIsActive(updateUsersReqDto.getIsActive());
		users.setVersion(updateUsersReqDto.getVersion());
		updateResDataDto.setVersion(userDao.update(users).getVersion());
		updateUsersResDto.setData(updateResDataDto);
		updateUsersResDto.setMessage(Message.SUCCESS.getNames());
		return (T) updateUsersResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
		
	public <T> T deleteById(Long id) throws Exception {
		DeleteUsersResDto deleteUsersResDto = new DeleteUsersResDto();
		userDao.deleteById(id);
		deleteUsersResDto.setMessage(Message.SUCCESS.getNames());
		return (T) deleteUsersResDto;

	}

	@Override
	public Users getByUsernames(String usernames) throws Exception {
		Users users = userDao.getByUsername(usernames);
		return users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Users user = getByUsernames(username);
			return new User(user.getUsername(), user.getPass(), new ArrayList<>());
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

}
