package com.rayhan.shippinghan.dao;

import com.rayhan.shippinghan.model.Shipment;
import java.util.List;

public interface ShipmentDao{
	List<Shipment> getAll(Long branchId,String statusCode) throws Exception;
	Shipment getById(Long id) throws Exception;
	Shipment insert(Shipment data) throws Exception;
	Shipment update(Shipment data) throws Exception;
	Integer countData ()throws Exception;
}
