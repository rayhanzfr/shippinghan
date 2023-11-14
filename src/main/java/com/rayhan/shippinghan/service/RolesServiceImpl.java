package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.constant.Message;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayhan.shippinghan.dao.RolesDao;
import com.rayhan.shippinghan.dto.InsertResDataDto;
import com.rayhan.shippinghan.dto.UpdateResDataDto;
import com.rayhan.shippinghan.dto.roles.DeleteRolesResDto;
import com.rayhan.shippinghan.dto.roles.GetAllRolesResDto;
import com.rayhan.shippinghan.dto.roles.GetByRolesIdResDto;
import com.rayhan.shippinghan.dto.roles.GetRolesDataDto;
import com.rayhan.shippinghan.dto.roles.InsertRolesReqDto;
import com.rayhan.shippinghan.dto.roles.InsertRolesResDto;
import com.rayhan.shippinghan.dto.roles.UpdateRolesReqDto;
import com.rayhan.shippinghan.dto.roles.UpdateRolesResDto;
import com.rayhan.shippinghan.model.Roles;

@Service
public class RolesServiceImpl extends BaseServiceImpl implements RolesService {
	private RolesDao rolesDao;
//	private AuthPrincipal authPrincipal;

	@Autowired
	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

//	@Autowired
//	public void setAuthPrincipal(AuthPrincipal authPrincipal) {
//		this.authPrincipal = authPrincipal;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAll() throws Exception {
		GetAllRolesResDto getAllRolesResDto = new GetAllRolesResDto();
		List<GetRolesDataDto> listGetRolesDataDto = new ArrayList<>();
		List<Roles> listRoles = rolesDao.getAll();
		listRoles.forEach(r -> {
			GetRolesDataDto getRolesDataDto = new GetRolesDataDto();
			getRolesDataDto.setId(r.getId());
			getRolesDataDto.setCode(r.getRoleCode());
			getRolesDataDto.setNames(r.getNames());
			getRolesDataDto.setCreatedBy(r.getId());
			getRolesDataDto.setCreatedDate(r.getCreatedDate());
			getRolesDataDto.setUpdateBy(r.getUpdateBy());
			getRolesDataDto.setUpdateDate(r.getUpdateDate());
			getRolesDataDto.setIsActive(r.getIsActive());
			getRolesDataDto.setVersion(r.getVersion());
			listGetRolesDataDto.add(getRolesDataDto);
		});
		getAllRolesResDto.setData(listGetRolesDataDto);
		return (T) getAllRolesResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Long id) throws Exception {
		GetByRolesIdResDto getByRoleIdResDto = new GetByRolesIdResDto();
		GetRolesDataDto getRolesDataDto = new GetRolesDataDto();
		Roles roles = new Roles();
		roles = rolesDao.getById(id);
		getRolesDataDto.setId(roles.getId());
		getRolesDataDto.setCode(roles.getRoleCode());
		getRolesDataDto.setNames(roles.getNames());
		getRolesDataDto.setCreatedBy(roles.getId());
		getRolesDataDto.setCreatedDate(roles.getCreatedDate());
		getRolesDataDto.setUpdateBy(roles.getUpdateBy());
		getRolesDataDto.setUpdateDate(roles.getUpdateDate());
		getRolesDataDto.setIsActive(roles.getIsActive());
		getRolesDataDto.setVersion(roles.getVersion());
		getByRoleIdResDto.setData(getRolesDataDto);
		return (T) getByRoleIdResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T insert(R data) throws Exception {
		InsertRolesResDto insertRoleResDto = new InsertRolesResDto();
		;
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		InsertRolesReqDto insertRolesReqDto = (InsertRolesReqDto) data;
		Roles roles = new Roles();
		roles.setRoleCode(insertRolesReqDto.getCode());
		roles.setNames(insertRolesReqDto.getNames());
		roles.setCreatedBy(getIdAuth());
		roles.setIsActive(insertRolesReqDto.getIsActive());
		insertResDataDto.setId(rolesDao.insert(roles).getId());
		insertRoleResDto.setData(insertResDataDto);
		insertRoleResDto.setMessage(Message.SUCCESS.getNames());
		return (T) insertRoleResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T update(R data) throws Exception {
		UpdateRolesResDto updateRolesResDto = new UpdateRolesResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		UpdateRolesReqDto updateRolesReqDto = (UpdateRolesReqDto) data;
		Roles roles = rolesDao.getById(updateRolesReqDto.getId());
		roles.setNames(updateRolesReqDto.getNames());
		roles.setUpdateBy(getIdAuth());
		roles.setIsActive(updateRolesReqDto.getIsActive());
		roles.setVersion(updateRolesReqDto.getVersion());
		updateResDataDto.setVersion(rolesDao.update(roles).getVersion());
		updateRolesResDto.setData(updateResDataDto);
		updateRolesResDto.setMessage(Message.SUCCESS.getNames());
		return (T) updateRolesResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T> T deleteById(Long id) throws Exception {
		DeleteRolesResDto deleteRolesResDto = new DeleteRolesResDto();
		rolesDao.deleteById(id);
		deleteRolesResDto.setMessage(Message.SUCCESS.getNames());
		return (T) deleteRolesResDto;

	}

}
