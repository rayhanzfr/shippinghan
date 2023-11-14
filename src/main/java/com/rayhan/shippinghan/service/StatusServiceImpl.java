package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.constant.Message;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayhan.shippinghan.dao.StatusDao;
import com.rayhan.shippinghan.dto.InsertResDataDto;
import com.rayhan.shippinghan.dto.UpdateResDataDto;
import com.rayhan.shippinghan.dto.status.DeleteStatusResDto;
import com.rayhan.shippinghan.dto.status.GetAllStatusResDto;
import com.rayhan.shippinghan.dto.status.GetByStatusIdResDto;
import com.rayhan.shippinghan.dto.status.GetStatusDataDto;
import com.rayhan.shippinghan.dto.status.InsertStatusReqDto;
import com.rayhan.shippinghan.dto.status.InsertStatusResDto;
import com.rayhan.shippinghan.dto.status.UpdateStatusReqDto;
import com.rayhan.shippinghan.dto.status.UpdateStatusResDto;
import com.rayhan.shippinghan.model.Status;

@Service
public class StatusServiceImpl implements StatusService {
	private StatusDao statusDao;
	@Autowired
	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAll() throws Exception {
		GetAllStatusResDto getAllStatusResDto = new GetAllStatusResDto();
		List<GetStatusDataDto> listGetStatusDataDto = new ArrayList<>();
		List<Status> listStatus = statusDao.getAll();
		listStatus.forEach(r ->{
			GetStatusDataDto getStatusDataDto = new GetStatusDataDto();
			getStatusDataDto.setId(r.getId());
			getStatusDataDto.setCode(r.getStatusCode());
			getStatusDataDto.setNames(r.getStatusName());
			getStatusDataDto.setCreatedBy(r.getId());
			getStatusDataDto.setCreatedDate(r.getCreatedDate());
			getStatusDataDto.setUpdateBy(r.getUpdateBy());
			getStatusDataDto.setUpdateDate(r.getUpdateDate());
			getStatusDataDto.setIsActive(r.getIsActive());
			getStatusDataDto.setVersion(r.getVersion());
			listGetStatusDataDto.add(getStatusDataDto);
		});
		getAllStatusResDto.setData(listGetStatusDataDto);
		return (T) getAllStatusResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Long id) throws Exception {
		GetByStatusIdResDto getByStatusIdResDto = new GetByStatusIdResDto();
		GetStatusDataDto getStatusDataDto = new GetStatusDataDto();
		Status status = new Status();
		status = statusDao.getById(id);
		getStatusDataDto.setCode(status.getStatusCode());
		getStatusDataDto.setNames(status.getStatusName());
		getStatusDataDto.setCreatedBy(status.getId());
		getStatusDataDto.setCreatedDate(status.getCreatedDate());
		getStatusDataDto.setUpdateBy(status.getUpdateBy());
		getStatusDataDto.setUpdateDate(status.getUpdateDate());
		getStatusDataDto.setIsActive(status.getIsActive());
		getStatusDataDto.setVersion(status.getVersion());
		getByStatusIdResDto.setData(getStatusDataDto);
		return (T) getByStatusIdResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T,R> T insert(R data) throws Exception {
		InsertStatusResDto insertStatusResDto = new InsertStatusResDto();;
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		InsertStatusReqDto insertStatusReqDto = (InsertStatusReqDto) data;
		Status status = new Status();
		status.setStatusCode(insertStatusReqDto.getCode());
		status.setStatusName(insertStatusReqDto.getNames());
		status.setCreatedBy(insertStatusReqDto.getCreateBy());
		status.setIsActive(insertStatusReqDto.getIsActive());
		Long id = statusDao.insert(status).getId();
		insertResDataDto.setId(id);
		insertStatusResDto.setData(insertResDataDto);
		insertStatusResDto.setMessage(Message.SUCCESS.getNames());
		return (T) insertStatusResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T,R> T update(R data) throws Exception {
		UpdateStatusResDto updateStatusResDto = new UpdateStatusResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		UpdateStatusReqDto updateStatusReqDto = (UpdateStatusReqDto) data;
		Status status = statusDao.getById(updateStatusReqDto.getId());
		status.setStatusName(updateStatusReqDto.getNames());
		status.setUpdateBy(updateStatusReqDto.getUpdateBy());
		status.setIsActive(updateStatusReqDto.getIsActive());
		status.setVersion(updateStatusReqDto.getVersion());
		updateResDataDto.setVersion(statusDao.update(status).getVersion());
		updateStatusResDto.setData(updateResDataDto);
		updateStatusResDto.setMessage(Message.SUCCESS.getNames());
		return (T) updateStatusResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T> T deleteById(Long id) throws Exception {
		DeleteStatusResDto deleteStatusResDto = new DeleteStatusResDto();
		statusDao.deleteById(id);
		deleteStatusResDto.setMessage(Message.SUCCESS.getNames());
		return (T) deleteStatusResDto;
		
	}

	@Override
	public GetByStatusIdResDto getByStatusCode(String statusCode) throws Exception {
		GetByStatusIdResDto getByStatusIdResDto = new GetByStatusIdResDto();
		GetStatusDataDto getStatusDataDto = new GetStatusDataDto();
		Status status = new Status();
		status = statusDao.getByStatusCode(statusCode);
		getStatusDataDto.setId(status.getId());
		getStatusDataDto.setCode(status.getStatusCode());
		getStatusDataDto.setNames(status.getStatusName());
		getStatusDataDto.setCreatedBy(status.getId());
		getStatusDataDto.setCreatedDate(status.getCreatedDate());
		getStatusDataDto.setUpdateBy(status.getUpdateBy());
		getStatusDataDto.setUpdateDate(status.getUpdateDate());
		getStatusDataDto.setIsActive(status.getIsActive());
		getStatusDataDto.setVersion(status.getVersion());
		getByStatusIdResDto.setData(getStatusDataDto);
		return getByStatusIdResDto;
	}

}
