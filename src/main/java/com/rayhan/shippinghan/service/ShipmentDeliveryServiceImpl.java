package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.constant.Message;
import com.rayhan.shippinghan.constant.StatusType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayhan.shippinghan.dao.ShipmentDao;
import com.rayhan.shippinghan.dao.ShipmentDeliveryDao;
import com.rayhan.shippinghan.dao.StatusDao;
import com.rayhan.shippinghan.dto.InsertResDataDto;
import com.rayhan.shippinghan.dto.UpdateResDataDto;
import com.rayhan.shippinghan.dto.shipmentdelivery.GetAllShipmentDeliveryResDto;
import com.rayhan.shippinghan.dto.shipmentdelivery.GetByShipmentDeliveryIdResDto;
import com.rayhan.shippinghan.dto.shipmentdelivery.GetShipmentDeliveryDataDto;
import com.rayhan.shippinghan.dto.shipmentdelivery.InsertShipmentDeliveryReqDto;
import com.rayhan.shippinghan.dto.shipmentdelivery.InsertShipmentDeliveryResDto;
import com.rayhan.shippinghan.dto.shipmentdelivery.UpdateStatusShipmentDeliveryReqDto;
import com.rayhan.shippinghan.dto.shipmentdelivery.UpdateStatusShipmentDeliveryResDto;
import com.rayhan.shippinghan.helper.DeliveryHelper;
import com.rayhan.shippinghan.model.Shipment;
import com.rayhan.shippinghan.model.ShipmentDelivery;
import com.rayhan.shippinghan.model.Status;

@Service
public class ShipmentDeliveryServiceImpl extends BaseServiceImpl implements ShipmentDeliveryService {
	private ShipmentDeliveryDao shipmentDeliveryDao;
	private ShipmentDao shipmentDao;
	private StatusDao statusDao;

	@Autowired
	public void setShipmentDeliveryDao(ShipmentDeliveryDao shipmentDeliveryDao) {
		this.shipmentDeliveryDao = shipmentDeliveryDao;
	}

	@Autowired
	public void setShipmentDao(ShipmentDao shipmentDao) {
		this.shipmentDao = shipmentDao;
	}

	@Autowired
	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAll() throws Exception {
		GetAllShipmentDeliveryResDto getAllShipmentDeliveryResDto = new GetAllShipmentDeliveryResDto();
		List<GetShipmentDeliveryDataDto> listGetShipmentDeliveryDataDto = new ArrayList<>();
		List<ShipmentDelivery> listShipmentDelivery = shipmentDeliveryDao.getAll();
		listShipmentDelivery.forEach(r -> {
			GetShipmentDeliveryDataDto getShipmentDeliveryDataDto = new GetShipmentDeliveryDataDto();
			getShipmentDeliveryDataDto.setId(r.getId());
			getShipmentDeliveryDataDto.setShipmentNumber(r.getShipment().getShipmentNumber());
			getShipmentDeliveryDataDto.setShipmentId(r.getShipment().getId());
			getShipmentDeliveryDataDto.setStatusName(r.getStatus().getStatusName());
			getShipmentDeliveryDataDto.setStatusId(r.getStatus().getId());
			getShipmentDeliveryDataDto.setReceiverName(r.getReceiverName());
			getShipmentDeliveryDataDto.setTimeDelivered(r.getTimeDelivered());
			getShipmentDeliveryDataDto.setCreatedBy(r.getCreatedBy());
			getShipmentDeliveryDataDto.setCreatedDate(r.getCreatedDate());
			getShipmentDeliveryDataDto.setUpdateBy(r.getUpdateBy());
			getShipmentDeliveryDataDto.setUpdateDate(r.getUpdateDate());
			getShipmentDeliveryDataDto.setIsActive(r.getIsActive());
			getShipmentDeliveryDataDto.setVersion(r.getVersion());
			listGetShipmentDeliveryDataDto.add(getShipmentDeliveryDataDto);
		});
		getAllShipmentDeliveryResDto.setData(listGetShipmentDeliveryDataDto);
		return (T) getAllShipmentDeliveryResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Long id) throws Exception {
		GetByShipmentDeliveryIdResDto getByShipmentDeliveryIdResDto = new GetByShipmentDeliveryIdResDto();
		GetShipmentDeliveryDataDto getShipmentDeliveryDataDto = new GetShipmentDeliveryDataDto();
		ShipmentDelivery shipmentDelivery = shipmentDeliveryDao.getById(id);
		getShipmentDeliveryDataDto.setId(shipmentDelivery.getId());
		getShipmentDeliveryDataDto.setShipmentId(shipmentDelivery.getShipment().getId());
		getShipmentDeliveryDataDto.setStatusId(shipmentDelivery.getStatus().getId());
		getShipmentDeliveryDataDto.setReceiverName(shipmentDelivery.getReceiverName());
		getShipmentDeliveryDataDto.setTimeDelivered(shipmentDelivery.getTimeDelivered());
		getShipmentDeliveryDataDto.setCreatedBy(shipmentDelivery.getCreatedBy());
		getShipmentDeliveryDataDto.setCreatedDate(shipmentDelivery.getCreatedDate());
		getShipmentDeliveryDataDto.setUpdateBy(shipmentDelivery.getUpdateBy());
		getShipmentDeliveryDataDto.setUpdateDate(shipmentDelivery.getUpdateDate());
		getShipmentDeliveryDataDto.setIsActive(shipmentDelivery.getIsActive());
		getShipmentDeliveryDataDto.setVersion(shipmentDelivery.getVersion());
		getByShipmentDeliveryIdResDto.setData(getShipmentDeliveryDataDto);
		return (T) getByShipmentDeliveryIdResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T insert(R data) throws Exception {
		ShipmentDelivery shipmentDelivery = new ShipmentDelivery();
		InsertShipmentDeliveryResDto insertShipmentDeliveryResDto = new InsertShipmentDeliveryResDto();
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		InsertShipmentDeliveryReqDto insertShipmentDeliveryReqDto = (InsertShipmentDeliveryReqDto) data;
		Shipment shipment = shipmentDao.getById(insertShipmentDeliveryReqDto.getShipmentId());
		Status status = statusDao.getByStatusCode(StatusType.ODLVR.getStatusCode());
		shipmentDelivery.setShipment(shipment);
		shipmentDelivery.setStatus(status);
		shipmentDelivery.setReceiverName(insertShipmentDeliveryReqDto.getReceiverName());
		shipmentDelivery.setTimeDelivered(LocalDateTime.now());
		shipmentDelivery.setCreatedBy(getIdAuth());
		shipmentDelivery.setIsActive(true);
		shipment.setStatus(status);
		shipment.setReceiverName(insertShipmentDeliveryReqDto.getReceiverName());
		Long id = shipmentDeliveryDao.insert(shipmentDelivery).getId();
		insertResDataDto.setId(id);
		insertShipmentDeliveryResDto.setData(insertResDataDto);
		insertShipmentDeliveryResDto.setMessage(Message.SUCCESS.getNames());
		shipmentDao.update(shipment);
		return (T) insertShipmentDeliveryResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T update(R data) throws Exception {
		ShipmentDelivery shipmentDelivery = new ShipmentDelivery();
		UpdateStatusShipmentDeliveryResDto updateShipmentDeliveryResDto = new UpdateStatusShipmentDeliveryResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		UpdateStatusShipmentDeliveryReqDto updateShipmentDeliveryReqDto = (UpdateStatusShipmentDeliveryReqDto) data;
		Shipment shipment = shipmentDao.getById(updateShipmentDeliveryReqDto.getShipmentId());
		Status status = statusDao.getById(updateShipmentDeliveryReqDto.getStatusId());
		shipmentDelivery = shipmentDeliveryDao.getById(updateShipmentDeliveryReqDto.getId());
		shipmentDelivery.setStatus(status);
		shipmentDelivery.setReceiverName(updateShipmentDeliveryReqDto.getReceiverName());
		shipmentDelivery.setUpdateBy(updateShipmentDeliveryReqDto.getUpdateBy());
		shipmentDelivery.setIsActive(true);
		shipmentDelivery.setVersion(updateShipmentDeliveryReqDto.getVersion());
		Integer version = shipmentDeliveryDao.update(shipmentDelivery).getVersion();
		shipment.setStatus(status);
		shipment.setReceiverName(updateShipmentDeliveryReqDto.getReceiverName());
		shipmentDao.update(shipment);
		updateResDataDto.setVersion(version);
		updateShipmentDeliveryResDto.setData(updateResDataDto);
		updateShipmentDeliveryResDto.setMessage(Message.SUCCESS.getNames());
		return (T) updateShipmentDeliveryResDto;
	}

	@Override
	public List<DeliveryHelper> getByCreatedBy(Long createdBy) throws Exception {
		return shipmentDeliveryDao.getByCreatedBy(createdBy);
	}

	@Override
	public List<ShipmentDelivery> getByCreatedByAndStatus(Long createdBy, Long idStatus) throws Exception {
		return shipmentDeliveryDao.getByCreatedByAndStatus(createdBy, idStatus);
	}

}
