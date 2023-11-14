package com.rayhan.shippinghan.constant;

public enum StatusType {
	//by BK not ID
	
	ODLVR("STATUS001","On Delivery"),DLVRD("STATUS002","Delivered"),PNDNG("STATUS003","Pending"),MISSROUTE("STATUS004","MISSROUTE"),
	CRISCROSS("STATUS005","Criss Cross"),GOOD("STATUS006","Good"),BROKEN("STATUS007","Broken"),NEW("STATUS008","New Entry");
	
	private String statusCode;
	private String names;
	
	private StatusType(String statusCode,String names) {
		this.statusCode=statusCode;
		this.names=names;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
}
