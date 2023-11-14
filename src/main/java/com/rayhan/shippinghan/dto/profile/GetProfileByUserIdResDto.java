package com.rayhan.shippinghan.dto.profile;

public class GetProfileByUserIdResDto {
	private GetProfileDataDto data;
	private String message;

	public GetProfileDataDto getData() {
		return data;
	}

	public void setData(GetProfileDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
