package com.rayhan.shippinghan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayhan.shippinghan.dao.ShipmentDetailDao;
import com.rayhan.shippinghan.dto.shipment.GetAllShipmentDetailResDto;
import com.rayhan.shippinghan.dto.shipment.GetByShipmentDetailIdResDto;
import com.rayhan.shippinghan.dto.shipment.GetDetailByShipmentIdResDto;
import com.rayhan.shippinghan.dto.shipment.GetShipmentDetailDataDto;
import com.rayhan.shippinghan.model.ShipmentDetail;

@Service
public class ShipmentDetailServiceImpl implements ShipmentDetailService {
	private ShipmentDetailDao shipmentDetailDao;

	@Autowired
	public void setShipmentDetailDao(ShipmentDetailDao shipmentDetailDao) {
		this.shipmentDetailDao = shipmentDetailDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAll() throws Exception {
		GetAllShipmentDetailResDto getAllShipmentDetailResDto = new GetAllShipmentDetailResDto();
		List<GetShipmentDetailDataDto> listGetShipmentDetailDataDto = new ArrayList<>();
		List<ShipmentDetail> listShipmentDetail = shipmentDetailDao.getAll();
		listShipmentDetail.forEach(i -> {
			GetShipmentDetailDataDto getShipmentDetailDataDto = new GetShipmentDetailDataDto();
			getShipmentDetailDataDto.setId(i.getId());
			getShipmentDetailDataDto.setShipmentId(i.getShipment().getId());
			getShipmentDetailDataDto.setCategoryId(i.getCategory().getId());
			getShipmentDetailDataDto.setItemNames(i.getItemName());
			getShipmentDetailDataDto.setWeight(i.getWeight());
			getShipmentDetailDataDto.setQuantity(i.getQuantity());
			listGetShipmentDetailDataDto.add(getShipmentDetailDataDto);
		});
		getAllShipmentDetailResDto.setData(listGetShipmentDetailDataDto);
		return (T) getAllShipmentDetailResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Long id) throws Exception {
		GetByShipmentDetailIdResDto getByShipmentDetailIdResDto = new GetByShipmentDetailIdResDto();
		GetShipmentDetailDataDto getShipmentDetailDataDto = new GetShipmentDetailDataDto();
		ShipmentDetail shipmentDetail = shipmentDetailDao.getById(id);
		getShipmentDetailDataDto.setId(shipmentDetail.getId());
		getShipmentDetailDataDto.setShipmentId(shipmentDetail.getShipment().getId());
		getShipmentDetailDataDto.setCategoryId(shipmentDetail.getCategory().getId());
		getShipmentDetailDataDto.setItemNames(shipmentDetail.getItemName());
		getShipmentDetailDataDto.setWeight(shipmentDetail.getWeight());
		getShipmentDetailDataDto.setQuantity(shipmentDetail.getQuantity());
		getByShipmentDetailIdResDto.setData(getShipmentDetailDataDto);
		return (T) getByShipmentDetailIdResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getByShipmentId(Long shipmentId) throws Exception {
		GetDetailByShipmentIdResDto getDetailByShipmentIdResDto = new GetDetailByShipmentIdResDto();
		List<GetShipmentDetailDataDto> listGetShipmentDetailDataDto = new ArrayList<>();
		List<ShipmentDetail> listShipmentDetails = shipmentDetailDao.getByShipmentId(shipmentId);
		listShipmentDetails.forEach(i -> {
			GetShipmentDetailDataDto getShipmentDetailDataDto = new GetShipmentDetailDataDto();
			getShipmentDetailDataDto.setId(i.getId());
			getShipmentDetailDataDto.setShipmentId(i.getShipment().getId());
			getShipmentDetailDataDto.setCategoryId(i.getCategory().getId());
			getShipmentDetailDataDto.setItemNames(i.getItemName());
			getShipmentDetailDataDto.setWeight(i.getWeight());
			getShipmentDetailDataDto.setQuantity(i.getQuantity());
			listGetShipmentDetailDataDto.add(getShipmentDetailDataDto);
		});
		getDetailByShipmentIdResDto.setData(listGetShipmentDetailDataDto);
		return (T) getDetailByShipmentIdResDto;
	}

}
