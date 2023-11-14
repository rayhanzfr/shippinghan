package com.rayhan.shippinghan.dto.shipmentdelivery;

public class GetByShipmentDeliveryIdResDto {
	private GetShipmentDeliveryDataDto data;
	private String message;

	public GetShipmentDeliveryDataDto getData() {
		return data;
	}

	public void setData(GetShipmentDeliveryDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
