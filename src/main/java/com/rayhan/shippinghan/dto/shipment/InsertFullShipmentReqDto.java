package com.rayhan.shippinghan.dto.shipment;

import java.util.List;

public class InsertFullShipmentReqDto {
	private InsertShipmentReqDto insertShipmentReqDto;
	private List<InsertShipmentDetailReqDto> listInsertShipmentDetailReqDto;
	
	public InsertShipmentReqDto getInsertShipmentReqDto() {
		return insertShipmentReqDto;
	}
	public void setInsertShipmentReqDto(InsertShipmentReqDto insertShipmentReqDto) {
		this.insertShipmentReqDto = insertShipmentReqDto;
	}
	public List<InsertShipmentDetailReqDto> getListInsertShipmentDetailReqDto() {
		return listInsertShipmentDetailReqDto;
	}
	public void setListInsertShipmentDetailReqDto(List<InsertShipmentDetailReqDto> listInsertShipmentDetailReqDto) {
		this.listInsertShipmentDetailReqDto = listInsertShipmentDetailReqDto;
	}
	
	
	
}
