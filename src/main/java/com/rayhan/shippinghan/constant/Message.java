package com.rayhan.shippinghan.constant;

public enum Message {
	SUCCESS("Success"),FAILED("Failed");
	
	private String names;
	
	Message(String names) {
		this.names = names;
	}
	public String getNames() {
		return names;
	}
}
