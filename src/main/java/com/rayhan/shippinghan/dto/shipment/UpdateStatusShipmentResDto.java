package com.rayhan.shippinghan.dto.shipment;

import com.rayhan.shippinghan.dto.UpdateResDataDto;

public class UpdateStatusShipmentResDto {
	private UpdateResDataDto data;
	private String message;
	
	public UpdateResDataDto getData() {
		return data;
	}
	public void setData(UpdateResDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
