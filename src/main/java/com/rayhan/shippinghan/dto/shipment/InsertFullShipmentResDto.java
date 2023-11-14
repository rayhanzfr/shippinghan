package com.rayhan.shippinghan.dto.shipment;

public class InsertFullShipmentResDto {
	private InsertShipmentResDto data;
	private String message;
	
	public InsertShipmentResDto getData() {
		return data;
	}
	public void setData(InsertShipmentResDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
