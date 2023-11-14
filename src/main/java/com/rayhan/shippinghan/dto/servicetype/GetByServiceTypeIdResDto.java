package com.rayhan.shippinghan.dto.servicetype;

public class GetByServiceTypeIdResDto {
	private GetServiceTypeDataDto data;
    private String message;
	
    public GetServiceTypeDataDto getData() {
		return data;
	}
	public void setData(GetServiceTypeDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    
}
