package com.rayhan.shippinghan.dto.city;

import java.util.List;

public class GetAllCityResDto {
	private List<GetCityDataDto> data;
    private String message;
	
    public List<GetCityDataDto> getData() {
		return data;
	}
    public void setData(List<GetCityDataDto> data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
