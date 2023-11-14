package com.rayhan.shippinghan.dao;

import com.rayhan.shippinghan.model.ShipmentDetail;
import java.util.List;

public interface ShipmentDetailDao extends BaseMasterDao<ShipmentDetail>{
	List<ShipmentDetail> getByShipmentId(Long shipmentId)throws Exception;
}
