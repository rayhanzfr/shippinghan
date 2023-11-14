package com.rayhan.shippinghan.dao;

import com.rayhan.shippinghan.helper.DeliveryHelper;
import com.rayhan.shippinghan.model.ShipmentDelivery;
import java.util.List;

public interface ShipmentDeliveryDao extends BaseMasterDao<ShipmentDelivery>{
	List<DeliveryHelper> getByCreatedBy(Long createdBy) throws Exception;

	List<ShipmentDelivery> getByCreatedByAndStatus(Long createdBy, Long idStatus) throws Exception;
}
