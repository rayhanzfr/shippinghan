package com.rayhan.shippinghan.service;

public interface ShipmentDetailService {
	<T>T getAll() throws Exception;
	<T>T getById(Long id) throws Exception;
	<T>T getByShipmentId(Long shipmentId)throws Exception;
}
