package com.rayhan.shippinghan.dto.defaultprice;

import java.util.List;

public class GetAllDefaultPriceResDto {
	private List<GetDefaultPriceDataDto> data;
    private String message;
	
    public List<GetDefaultPriceDataDto> getData() {
		return data;
	}
    public void setData(List<GetDefaultPriceDataDto> data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
