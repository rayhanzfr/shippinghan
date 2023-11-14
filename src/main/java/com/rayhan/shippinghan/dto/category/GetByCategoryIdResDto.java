package com.rayhan.shippinghan.dto.category;

public class GetByCategoryIdResDto {
	private GetCategoryDataDto data;
    private String message;
	
    public GetCategoryDataDto getData() {
		return data;
	}
	public void setData(GetCategoryDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    
}
