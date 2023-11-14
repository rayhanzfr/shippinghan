package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.constant.Message;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayhan.shippinghan.dao.BranchDao;
import com.rayhan.shippinghan.dao.DefaultPriceDao;
import com.rayhan.shippinghan.dao.ServiceTypeDao;
import com.rayhan.shippinghan.dto.InsertResDataDto;
import com.rayhan.shippinghan.dto.UpdateResDataDto;
import com.rayhan.shippinghan.dto.defaultprice.DeleteDefaultPriceResDto;
import com.rayhan.shippinghan.dto.defaultprice.GetAllDefaultPriceResDto;
import com.rayhan.shippinghan.dto.defaultprice.GetByDefaultPriceIdResDto;
import com.rayhan.shippinghan.dto.defaultprice.GetDefaultPriceDataDto;
import com.rayhan.shippinghan.dto.defaultprice.InsertDefaultPriceReqDto;
import com.rayhan.shippinghan.dto.defaultprice.InsertDefaultPriceResDto;
import com.rayhan.shippinghan.dto.defaultprice.UpdateDefaultPriceReqDto;
import com.rayhan.shippinghan.dto.defaultprice.UpdateDefaultPriceResDto;
import com.rayhan.shippinghan.model.Branch;
import com.rayhan.shippinghan.model.DefaultPrice;
import com.rayhan.shippinghan.model.ServiceType;

@Service
public class DefaultPriceServiceImpl implements DefaultPriceService{
	private DefaultPriceDao defaultPriceDao;
	private BranchDao branchDao;
	private ServiceTypeDao serviceTypeDao;
	
	@Autowired
	public void setDefaultPriceDao(DefaultPriceDao defaultPriceDao) {
		this.defaultPriceDao = defaultPriceDao;
	}
	@Autowired
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}
	@Autowired
	public void setServiceTypeDao(ServiceTypeDao serviceTypeDao) {
		this.serviceTypeDao = serviceTypeDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAll() throws Exception {
		GetAllDefaultPriceResDto getAllDefaultPriceResDto = new GetAllDefaultPriceResDto();
		List<GetDefaultPriceDataDto> listGetDefaultPriceDataDto = new ArrayList<>();
		List<DefaultPrice> listDefaultPrice = defaultPriceDao.getAll();
		listDefaultPrice.forEach(r -> {
			GetDefaultPriceDataDto getDefaultPriceDataDto = new GetDefaultPriceDataDto();
			getDefaultPriceDataDto.setId(r.getId());
			getDefaultPriceDataDto.setBranchId(r.getBranch().getId());
			getDefaultPriceDataDto.setServiceTypeId(r.getServiceType().getId());
			getDefaultPriceDataDto.setCode(r.getDefaultPriceCode());
			getDefaultPriceDataDto.setPrice(r.getPrice());
			getDefaultPriceDataDto.setCreatedBy(r.getCreatedBy());
			getDefaultPriceDataDto.setCreatedDate(r.getCreatedDate());
			getDefaultPriceDataDto.setUpdateBy(r.getUpdateBy());
			getDefaultPriceDataDto.setUpdateDate(r.getUpdateDate());
			getDefaultPriceDataDto.setIsActive(r.getIsActive());
			getDefaultPriceDataDto.setVersion(r.getVersion());
			listGetDefaultPriceDataDto.add(getDefaultPriceDataDto);
		});
		getAllDefaultPriceResDto.setData(listGetDefaultPriceDataDto);
		return (T) getAllDefaultPriceResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Long id) throws Exception {
		GetByDefaultPriceIdResDto getByDefaultPriceIdResDto = new GetByDefaultPriceIdResDto();
		GetDefaultPriceDataDto getDefaultPriceDataDto = new GetDefaultPriceDataDto();
		DefaultPrice defaultPrice = new DefaultPrice();
		defaultPrice = defaultPriceDao.getById(id);
		getDefaultPriceDataDto.setId(defaultPrice.getId());
		getDefaultPriceDataDto.setBranchId(defaultPrice.getBranch().getId());
		getDefaultPriceDataDto.setServiceTypeId(defaultPrice.getServiceType().getId());
		getDefaultPriceDataDto.setCode(defaultPrice.getDefaultPriceCode());
		getDefaultPriceDataDto.setCreatedBy(defaultPrice.getCreatedBy());
		getDefaultPriceDataDto.setCreatedDate(defaultPrice.getCreatedDate());
		getDefaultPriceDataDto.setUpdateBy(defaultPrice.getUpdateBy());
		getDefaultPriceDataDto.setUpdateDate(defaultPrice.getUpdateDate());
		getDefaultPriceDataDto.setIsActive(defaultPrice.getIsActive());
		getDefaultPriceDataDto.setVersion(defaultPrice.getVersion());
		getByDefaultPriceIdResDto.setData(getDefaultPriceDataDto);
		return (T) getByDefaultPriceIdResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T insert(R data) throws Exception {
		DefaultPrice defaultPrice = new DefaultPrice();
		InsertDefaultPriceResDto insertDefaultPriceResDto = new InsertDefaultPriceResDto();
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		InsertDefaultPriceReqDto insertDefaultPriceReqDto = (InsertDefaultPriceReqDto) data;
		Branch branch = branchDao.getById(insertDefaultPriceReqDto.getBranchId());
		ServiceType serviceType = serviceTypeDao.getById(insertDefaultPriceReqDto.getServiceTypeId());
		defaultPrice.setBranch(branch);
		defaultPrice.setServiceType(serviceType);
		defaultPrice.setDefaultPriceCode(insertDefaultPriceReqDto.getCode());
		defaultPrice.setPrice(BigDecimal.valueOf(insertDefaultPriceReqDto.getPrice()));
		defaultPrice.setCreatedBy(insertDefaultPriceReqDto.getCreateBy());
		defaultPrice.setIsActive(insertDefaultPriceReqDto.getIsActive());
		Long id = defaultPriceDao.insert(defaultPrice).getId();
		insertResDataDto.setId(id);
		insertDefaultPriceResDto.setData(insertResDataDto);
		insertDefaultPriceResDto.setMessage(Message.SUCCESS.getNames());
		return (T) insertDefaultPriceResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T update(R data) throws Exception {
		DefaultPrice defaultPrice = new DefaultPrice();
		UpdateDefaultPriceResDto updateDefaultPriceResDto = new UpdateDefaultPriceResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		UpdateDefaultPriceReqDto updateDefaultPriceReqDto = (UpdateDefaultPriceReqDto) data;
		Branch branch = branchDao.getById(updateDefaultPriceReqDto.getBranchId());
		ServiceType serviceType = serviceTypeDao.getById(updateDefaultPriceReqDto.getServiceTypeId());
		defaultPrice = defaultPriceDao.getById(updateDefaultPriceReqDto.getId());
		defaultPrice.setPrice(BigDecimal.valueOf(updateDefaultPriceReqDto.getPrice()));
		defaultPrice.setBranch(branch);
		defaultPrice.setServiceType(serviceType);
		defaultPrice.setUpdateBy(updateDefaultPriceReqDto.getUpdateBy());
		defaultPrice.setIsActive(updateDefaultPriceReqDto.getIsActive());
		defaultPrice.setVersion(updateDefaultPriceReqDto.getVersion());
		Integer version = defaultPriceDao.update(defaultPrice).getVersion();
		updateResDataDto.setVersion(version);
		updateDefaultPriceResDto.setData(updateResDataDto);
		updateDefaultPriceResDto.setMessage(Message.SUCCESS.getNames());
		return (T) updateDefaultPriceResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T> T deleteById(Long id) throws Exception {
		DeleteDefaultPriceResDto deleteDefaultPriceResDto = new DeleteDefaultPriceResDto();
		defaultPriceDao.deleteById(id);
		deleteDefaultPriceResDto.setMessage(Message.SUCCESS.getNames());
		return (T) deleteDefaultPriceResDto;

	}

}
