package com.rayhan.shippinghan.validate;

import java.math.BigDecimal;

public interface InputValidate {
	
	public boolean validatePhoneNumber(String phoneNumber) throws Exception;
	public boolean validatePay(BigDecimal price, BigDecimal money) throws Exception;
	public boolean validateListSize(Integer listSize) throws Exception;
	public boolean validateListChoosen(Integer listSize, Integer listChoosen) throws Exception;
	
}
