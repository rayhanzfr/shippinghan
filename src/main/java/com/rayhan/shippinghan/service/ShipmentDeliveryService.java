package com.rayhan.shippinghan.service;

import java.util.List;

import com.rayhan.shippinghan.helper.DeliveryHelper;
import com.rayhan.shippinghan.model.ShipmentDelivery;

public interface ShipmentDeliveryService{
	<T>T getAll() throws Exception;

	<T>T getById(Long id) throws Exception;

	<T,R>T insert(R data) throws Exception;

	<T,R>T update(R data) throws Exception;
	
	List<DeliveryHelper> getByCreatedBy(Long createdBy) throws Exception;
	
	List<ShipmentDelivery> getByCreatedByAndStatus(Long createdBy, Long idStatus) throws Exception;
}
