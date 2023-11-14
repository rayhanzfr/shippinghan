package com.rayhan.shippinghan.dto.shipment;

import java.util.List;

public class GetAllShipmentResDto {
	private List<GetShipmentDataDto> header;
	
    public List<GetShipmentDataDto> getData() {
		return header;
	}
    public void setData(List<GetShipmentDataDto> data) {
		this.header = data;
	}
	
}
