package com.rayhan.shippinghan.dao;

import com.rayhan.shippinghan.model.Status;

public interface StatusDao extends BaseMasterDao<Status>{
	Status getByStatusCode(String statusCode) throws Exception;
}
