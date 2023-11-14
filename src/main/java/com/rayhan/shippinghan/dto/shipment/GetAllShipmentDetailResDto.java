package com.rayhan.shippinghan.dto.shipment;

import java.util.List;

public class GetAllShipmentDetailResDto {
	private List<GetShipmentDetailDataDto> detail;
	
    public List<GetShipmentDetailDataDto> getData() {
		return detail;
	}
    public void setData(List<GetShipmentDetailDataDto> data) {
		this.detail = data;
	}
	
}
