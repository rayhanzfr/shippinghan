package com.rayhan.shippinghan.validate;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class InputValidateImpl implements InputValidate {
	public boolean validatePhoneNumber(String phoneNumber) {
		try {
			Double.parseDouble(phoneNumber);
			if (phoneNumber.length() <= 13) {
				return true;
			}
			System.out.println("Input nomor telefon dengan angka dan paling banyak 12-digit");
			return false;
		} catch (NumberFormatException e) {
			System.out.println("Input nomor telefon dengan angka dan paling banyak 12-digit");
			return false;
		}
	}
	public boolean validatePay(BigDecimal price, BigDecimal money) {
		Integer compareMoney = price.compareTo(money);
		if (compareMoney == 0) {
			return true;
		} else if (compareMoney == -1) {
			System.out.println("Kembalian: " + money.subtract(price));
			return true;
		} else {
			System.out.println("Uangnya kurang");
		}
		return false;
	}
	public boolean validateListSize(Integer listSize)throws Exception {
		if (listSize!=0 && listSize!=null) {
			return true;
		}
		return false;
	}
	public boolean validateListChoosen(Integer listSize, Integer listChoosen) throws Exception{
		if(listChoosen<=listSize && listChoosen>0) {
			return true;
		}
		System.out.println("Tidak ditemukan");
		return false;
	}
}
