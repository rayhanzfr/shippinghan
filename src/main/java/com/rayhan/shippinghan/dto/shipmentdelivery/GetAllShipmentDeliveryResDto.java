package com.rayhan.shippinghan.dto.shipmentdelivery;

import java.util.List;

public class GetAllShipmentDeliveryResDto {
	private List<GetShipmentDeliveryDataDto> data;
	private String message;
	
	public List<GetShipmentDeliveryDataDto> getData() {
		return data;
	}
	public void setData(List<GetShipmentDeliveryDataDto> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
