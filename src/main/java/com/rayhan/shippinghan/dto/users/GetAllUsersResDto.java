package com.rayhan.shippinghan.dto.users;

import java.util.List;

public class GetAllUsersResDto {
	private List<GetUsersDataDto> data;
    private String message;
	
    public List<GetUsersDataDto> getData() {
		return data;
	}
    public void setData(List<GetUsersDataDto> data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
