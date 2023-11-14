package com.rayhan.shippinghan.dto.roles;

import java.util.List;

public class GetAllRolesResDto {
	private List<GetRolesDataDto> data;
    private String message;
	
    public List<GetRolesDataDto> getData() {
		return data;
	}
    public void setData(List<GetRolesDataDto> data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
