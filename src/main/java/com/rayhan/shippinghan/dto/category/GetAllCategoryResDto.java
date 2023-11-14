package com.rayhan.shippinghan.dto.category;

import java.util.List;

public class GetAllCategoryResDto {
	private List<GetCategoryDataDto> data;
    private String message;
	
    public List<GetCategoryDataDto> getData() {
		return data;
	}
    public void setData(List<GetCategoryDataDto> data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
