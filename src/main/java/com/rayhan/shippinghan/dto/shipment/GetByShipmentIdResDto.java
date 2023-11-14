package com.rayhan.shippinghan.dto.shipment;

public class GetByShipmentIdResDto {
	private GetShipmentDataDto data;
    private String message;
	
    public GetShipmentDataDto getData() {
		return data;
	}
	public void setData(GetShipmentDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    
}
