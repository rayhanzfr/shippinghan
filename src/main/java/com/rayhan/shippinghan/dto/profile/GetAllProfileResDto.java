package com.rayhan.shippinghan.dto.profile;

import java.util.List;

public class GetAllProfileResDto {
	private List<GetProfileDataDto> data;
    private String message;
	
    public List<GetProfileDataDto> getData() {
		return data;
	}
    public void setData(List<GetProfileDataDto> data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
