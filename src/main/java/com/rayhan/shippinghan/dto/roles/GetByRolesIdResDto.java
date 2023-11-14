package com.rayhan.shippinghan.dto.roles;

public class GetByRolesIdResDto {
	private GetRolesDataDto data;
    private String message;
	
    public GetRolesDataDto getData() {
		return data;
	}
	public void setData(GetRolesDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    
}
