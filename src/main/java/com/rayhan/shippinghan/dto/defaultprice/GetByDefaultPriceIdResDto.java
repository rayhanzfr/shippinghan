package com.rayhan.shippinghan.dto.defaultprice;

public class GetByDefaultPriceIdResDto {
	private GetDefaultPriceDataDto data;
    private String message;
	
    public GetDefaultPriceDataDto getData() {
		return data;
	}
	public void setData(GetDefaultPriceDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    
}
