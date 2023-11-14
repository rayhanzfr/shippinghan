package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.constant.Message;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayhan.shippinghan.dao.ServiceTypeDao;
import com.rayhan.shippinghan.dto.InsertResDataDto;
import com.rayhan.shippinghan.dto.UpdateResDataDto;
import com.rayhan.shippinghan.dto.servicetype.DeleteServiceTypeResDto;
import com.rayhan.shippinghan.dto.servicetype.GetAllServiceTypeResDto;
import com.rayhan.shippinghan.dto.servicetype.GetByServiceTypeIdResDto;
import com.rayhan.shippinghan.dto.servicetype.GetServiceTypeDataDto;
import com.rayhan.shippinghan.dto.servicetype.InsertServiceTypeReqDto;
import com.rayhan.shippinghan.dto.servicetype.InsertServiceTypeResDto;
import com.rayhan.shippinghan.dto.servicetype.UpdateServiceTypeReqDto;
import com.rayhan.shippinghan.dto.servicetype.UpdateServiceTypeResDto;
import com.rayhan.shippinghan.model.ServiceType;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

	private ServiceTypeDao serviceTypeDao;

	@Autowired
	public void setServiceTypeDao(ServiceTypeDao serviceTypeDao) {
		this.serviceTypeDao = serviceTypeDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAll() throws Exception {
		GetAllServiceTypeResDto getAllServiceTypeResDto = new GetAllServiceTypeResDto();
		List<GetServiceTypeDataDto> listGetServiceTypeDataDto = new ArrayList<>();
		List<ServiceType> listServiceType = serviceTypeDao.getAll();
		listServiceType.forEach(r -> {
			GetServiceTypeDataDto getServiceTypeDataDto = new GetServiceTypeDataDto();
			getServiceTypeDataDto.setId(r.getId());
			getServiceTypeDataDto.setCode(r.getServiceCode());
			getServiceTypeDataDto.setNames(r.getServiceName());
			getServiceTypeDataDto.setCreatedBy(r.getId());
			getServiceTypeDataDto.setCreatedDate(r.getCreatedDate());
			getServiceTypeDataDto.setUpdateBy(r.getUpdateBy());
			getServiceTypeDataDto.setUpdateDate(r.getUpdateDate());
			getServiceTypeDataDto.setIsActive(r.getIsActive());
			getServiceTypeDataDto.setVersion(r.getVersion());
			listGetServiceTypeDataDto.add(getServiceTypeDataDto);
		});
		getAllServiceTypeResDto.setData(listGetServiceTypeDataDto);
		return (T) getAllServiceTypeResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Long id) throws Exception {
		GetByServiceTypeIdResDto getByServiceTypeIdResDto = new GetByServiceTypeIdResDto();
		GetServiceTypeDataDto getServiceTypeDataDto = new GetServiceTypeDataDto();
		ServiceType serviceType = new ServiceType();
		serviceType = serviceTypeDao.getById(id);
		getServiceTypeDataDto.setCode(serviceType.getServiceCode());
		getServiceTypeDataDto.setNames(serviceType.getServiceName());
		getServiceTypeDataDto.setCreatedBy(serviceType.getId());
		getServiceTypeDataDto.setCreatedDate(serviceType.getCreatedDate());
		getServiceTypeDataDto.setUpdateBy(serviceType.getUpdateBy());
		getServiceTypeDataDto.setUpdateDate(serviceType.getUpdateDate());
		getServiceTypeDataDto.setIsActive(serviceType.getIsActive());
		getServiceTypeDataDto.setVersion(serviceType.getVersion());
		getByServiceTypeIdResDto.setData(getServiceTypeDataDto);
		return (T) getByServiceTypeIdResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T insert(R data) throws Exception {
		InsertServiceTypeResDto insertServiceTypeResDto = new InsertServiceTypeResDto();
		;
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		InsertServiceTypeReqDto insertServiceTypeReqDto = (InsertServiceTypeReqDto) data;
		ServiceType serviceType = new ServiceType();
		serviceType.setServiceCode(insertServiceTypeReqDto.getCode());
		serviceType.setServiceName(insertServiceTypeReqDto.getNames());
		serviceType.setCreatedBy(insertServiceTypeReqDto.getCreatedBy());
		serviceType.setIsActive(insertServiceTypeReqDto.getIsActive());
		Long id = serviceTypeDao.insert(serviceType).getId();
		insertResDataDto.setId(id);
		insertServiceTypeResDto.setData(insertResDataDto);
		insertServiceTypeResDto.setMessage(Message.SUCCESS.getNames());
		return (T) insertServiceTypeResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T update(R data) throws Exception {
		UpdateServiceTypeResDto updateServiceTypeResDto = new UpdateServiceTypeResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		UpdateServiceTypeReqDto updateServiceTypeReqDto = (UpdateServiceTypeReqDto) data;
		ServiceType serviceType = serviceTypeDao.getById(updateServiceTypeReqDto.getId());
		serviceType.setServiceName(updateServiceTypeReqDto.getNames());
		serviceType.setUpdateBy(updateServiceTypeReqDto.getUpdateBy());
		serviceType.setIsActive(updateServiceTypeReqDto.getIsActive());
		serviceType.setVersion(updateServiceTypeReqDto.getVersion());
		updateResDataDto.setVersion(serviceTypeDao.update(serviceType).getVersion());
		updateServiceTypeResDto.setData(updateResDataDto);
		updateServiceTypeResDto.setMessage(Message.SUCCESS.getNames());
		return (T) updateServiceTypeResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T> T deleteById(Long id) throws Exception {
		DeleteServiceTypeResDto deleteServiceTypeResDto = new DeleteServiceTypeResDto();
		serviceTypeDao.deleteById(id);
		deleteServiceTypeResDto.setMessage(Message.SUCCESS.getNames());
		return (T) deleteServiceTypeResDto;

	}

}
