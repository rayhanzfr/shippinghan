package com.rayhan.shippinghan.dto.servicetype;

import java.util.List;

public class GetAllServiceTypeResDto {
	private List<GetServiceTypeDataDto> data;
    private String message;
	
    public List<GetServiceTypeDataDto> getData() {
		return data;
	}
    public void setData(List<GetServiceTypeDataDto> data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
