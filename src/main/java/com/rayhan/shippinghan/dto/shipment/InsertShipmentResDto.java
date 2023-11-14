package com.rayhan.shippinghan.dto.shipment;

import com.rayhan.shippinghan.dto.InsertResDataDto;
import java.util.List;

public class InsertShipmentResDto {
	private InsertResDataDto header;
	private List<InsertShipmentDetailResDto> listDetail;
	
	public InsertResDataDto getHeader() {
		return header;
	}
	public void setHeader(InsertResDataDto header) {
		this.header = header;
	}
	public List<InsertShipmentDetailResDto> getListDetail() {
		return listDetail;
	}
	public void setListDetail(List<InsertShipmentDetailResDto> listDetail) {
		this.listDetail = listDetail;
	}
	
}
