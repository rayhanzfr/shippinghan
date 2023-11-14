package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.dto.shipment.GetAllShipmentResDto;
import com.rayhan.shippinghan.dto.shipment.GetByShipmentIdResDto;
import com.rayhan.shippinghan.dto.shipment.InsertFullShipmentReqDto;
import com.rayhan.shippinghan.dto.shipment.InsertFullShipmentResDto;
import com.rayhan.shippinghan.dto.shipment.UpdateStatusShipmentReqDto;
import com.rayhan.shippinghan.dto.shipment.UpdateStatusShipmentResDto;

public interface ShipmentService {
	GetAllShipmentResDto getAll(Long branchId, String statusCode) throws Exception;

	GetByShipmentIdResDto getById(Long id) throws Exception;

	InsertFullShipmentResDto insert(InsertFullShipmentReqDto data) throws Exception;

	UpdateStatusShipmentResDto update(UpdateStatusShipmentReqDto data) throws Exception;

	Integer countData() throws Exception;

}
