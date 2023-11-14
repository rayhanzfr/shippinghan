package com.rayhan.shippinghan.dto.city;

public class GetByCityIdResDto {
	private GetCityDataDto data;
    private String message;
	
    public GetCityDataDto getData() {
		return data;
	}
	public void setData(GetCityDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    
}
