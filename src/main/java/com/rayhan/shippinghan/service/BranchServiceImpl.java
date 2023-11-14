package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.constant.Message;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayhan.shippinghan.dao.BranchDao;
import com.rayhan.shippinghan.dao.CityDao;
import com.rayhan.shippinghan.dto.InsertResDataDto;
import com.rayhan.shippinghan.dto.UpdateResDataDto;
import com.rayhan.shippinghan.dto.branch.DeleteBranchResDto;
import com.rayhan.shippinghan.dto.branch.GetAllBranchResDto;
import com.rayhan.shippinghan.dto.branch.GetBranchDataDto;
import com.rayhan.shippinghan.dto.branch.GetByBranchIdResDto;
import com.rayhan.shippinghan.dto.branch.InsertBranchReqDto;
import com.rayhan.shippinghan.dto.branch.InsertBranchResDto;
import com.rayhan.shippinghan.dto.branch.UpdateBranchReqDto;
import com.rayhan.shippinghan.dto.branch.UpdateBranchResDto;
import com.rayhan.shippinghan.model.Branch;
import com.rayhan.shippinghan.model.City;

@Service
public class BranchServiceImpl implements BranchService {

	private BranchDao branchDao;
	private CityDao cityDao;

	@Autowired
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	@Autowired
	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAll() throws Exception {
		GetAllBranchResDto getAllBranchResDto = new GetAllBranchResDto();
		List<GetBranchDataDto> listGetBranchDataDto = new ArrayList<>();
		List<Branch> listBranch = branchDao.getAll();
		listBranch.forEach(r -> {
			GetBranchDataDto getBranchDataDto = new GetBranchDataDto();
			getBranchDataDto.setId(r.getId());
			getBranchDataDto.setCode(r.getBranchesCode());
			getBranchDataDto.setNames(r.getBranchesName());
			getBranchDataDto.setCityNames(r.getCity().getNames());
			getBranchDataDto.setCityId(r.getCity().getId());
			getBranchDataDto.setCreatedBy(r.getCreatedBy());
			getBranchDataDto.setCreatedDate(r.getCreatedDate());
			getBranchDataDto.setUpdateBy(r.getUpdateBy());
			getBranchDataDto.setUpdateDate(r.getUpdateDate());
			getBranchDataDto.setIsActive(r.getIsActive());
			getBranchDataDto.setVersion(r.getVersion());
			listGetBranchDataDto.add(getBranchDataDto);
		});
		getAllBranchResDto.setData(listGetBranchDataDto);
		return (T) getAllBranchResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Long id) throws Exception {
		GetByBranchIdResDto getByBranchIdResDto = new GetByBranchIdResDto();
		GetBranchDataDto getBranchDataDto = new GetBranchDataDto();
		Branch branch = new Branch();
		branch = branchDao.getById(id);
		getBranchDataDto.setId(branch.getId());
		getBranchDataDto.setCode(branch.getBranchesCode());
		getBranchDataDto.setNames(branch.getBranchesName());
		getBranchDataDto.setCityId(branch.getCity().getId());
		getBranchDataDto.setCreatedBy(branch.getCreatedBy());
		getBranchDataDto.setCreatedDate(branch.getCreatedDate());
		getBranchDataDto.setUpdateBy(branch.getUpdateBy());
		getBranchDataDto.setUpdateDate(branch.getUpdateDate());
		getBranchDataDto.setIsActive(branch.getIsActive());
		getBranchDataDto.setVersion(branch.getVersion());
		getByBranchIdResDto.setData(getBranchDataDto);
		return (T) getByBranchIdResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T insert(R data) throws Exception {
		Branch branch = new Branch();
		InsertBranchResDto insertBranchResDto = new InsertBranchResDto();
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		InsertBranchReqDto insertBranchReqDto = (InsertBranchReqDto) data;
		City city = cityDao.getById(insertBranchReqDto.getCityId());
		branch.setCity(city);
		branch.setBranchesCode(insertBranchReqDto.getCode());
		branch.setBranchesName(insertBranchReqDto.getNames());
		branch.setCreatedBy(insertBranchReqDto.getCreateBy());
		branch.setIsActive(insertBranchReqDto.getIsActive());
		Long id = branchDao.insert(branch).getId();
		insertResDataDto.setId(id);
		insertBranchResDto.setData(insertResDataDto);
		insertBranchResDto.setMessage(Message.SUCCESS.getNames());
		return (T) insertBranchResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T update(R data) throws Exception {
		City city = new City();
		Branch branch = new Branch();
		UpdateBranchResDto updateBranchResDto = new UpdateBranchResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		UpdateBranchReqDto updateBranchReqDto = (UpdateBranchReqDto) data;
		branch = branchDao.getById(updateBranchReqDto.getId());
		city = cityDao.getById(updateBranchReqDto.getCityId());
		branch.setCity(city);
		branch.setBranchesName(updateBranchReqDto.getNames());
		branch.setUpdateBy(updateBranchReqDto.getUpdateBy());
		branch.setIsActive(updateBranchReqDto.getIsActive());
		branch.setVersion(updateBranchReqDto.getVersion());
		Integer version = branchDao.update(branch).getVersion();
		updateResDataDto.setVersion(version);
		updateBranchResDto.setData(updateResDataDto);
		updateBranchResDto.setMessage(Message.SUCCESS.getNames());
		return (T) updateBranchResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T> T deleteById(Long id) throws Exception {
		DeleteBranchResDto deleteBranchResDto = new DeleteBranchResDto();
		branchDao.deleteById(id);
		deleteBranchResDto.setMessage(Message.SUCCESS.getNames());
		return (T) deleteBranchResDto;

	}

}
