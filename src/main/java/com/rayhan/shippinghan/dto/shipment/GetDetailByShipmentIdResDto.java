package com.rayhan.shippinghan.dto.shipment;

import java.util.List;

public class GetDetailByShipmentIdResDto {
	private List<GetShipmentDetailDataDto> data;
	
	public List<GetShipmentDetailDataDto> getData() {
		return data;
	}
	public void setData(List<GetShipmentDetailDataDto> data) {
		this.data = data;
	}
}
