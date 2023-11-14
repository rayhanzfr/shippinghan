package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.dto.status.GetByStatusIdResDto;

public interface StatusService extends BaseMasterService {

	GetByStatusIdResDto getByStatusCode(String statusCode) throws Exception;
}
