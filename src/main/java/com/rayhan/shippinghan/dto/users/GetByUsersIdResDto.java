package com.rayhan.shippinghan.dto.users;

public class GetByUsersIdResDto {
	private GetUsersDataDto data;
    private String message;
	
    public GetUsersDataDto getData() {
		return data;
	}
	public void setData(GetUsersDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    
}
