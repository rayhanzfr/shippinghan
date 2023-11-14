package com.rayhan.shippinghan.dto.users;

import com.rayhan.shippinghan.dto.InsertResDataDto;

public class InsertUsersResDto {
	private InsertResDataDto data;
	private String message;
	public InsertResDataDto getData() {
		return data;
	}
	public void setData(InsertResDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
