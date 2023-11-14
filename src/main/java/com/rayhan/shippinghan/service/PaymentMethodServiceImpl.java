package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.constant.Message;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayhan.shippinghan.dao.PaymentMethodDao;
import com.rayhan.shippinghan.dto.InsertResDataDto;
import com.rayhan.shippinghan.dto.UpdateResDataDto;
import com.rayhan.shippinghan.dto.paymentmethod.DeletePaymentMethodResDto;
import com.rayhan.shippinghan.dto.paymentmethod.GetAllPaymentMethodResDto;
import com.rayhan.shippinghan.dto.paymentmethod.GetByPaymentMethodIdResDto;
import com.rayhan.shippinghan.dto.paymentmethod.GetPaymentMethodDataDto;
import com.rayhan.shippinghan.dto.paymentmethod.InsertPaymentMethodReqDto;
import com.rayhan.shippinghan.dto.paymentmethod.InsertPaymentMethodResDto;
import com.rayhan.shippinghan.dto.paymentmethod.UpdatePaymentMethodReqDto;
import com.rayhan.shippinghan.dto.paymentmethod.UpdatePaymentMethodResDto;
import com.rayhan.shippinghan.model.PaymentMethod;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
	private PaymentMethodDao paymentMethodDao;

	@Autowired
	public void setPaymentMethodDao(PaymentMethodDao paymentMethodDao) {
		this.paymentMethodDao = paymentMethodDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAll() throws Exception {
		GetAllPaymentMethodResDto getAllPaymentMethodResDto = new GetAllPaymentMethodResDto();
		List<GetPaymentMethodDataDto> listGetPaymentMethodDataDto = new ArrayList<>();
		List<PaymentMethod> listPaymentMethod = paymentMethodDao.getAll();
		listPaymentMethod.forEach(r -> {
			GetPaymentMethodDataDto getPaymentMethodDataDto = new GetPaymentMethodDataDto();
			getPaymentMethodDataDto.setId(r.getId());
			getPaymentMethodDataDto.setCode(r.getPaymentCode());
			getPaymentMethodDataDto.setNames(r.getPaymentName());
			getPaymentMethodDataDto.setCreatedBy(r.getId());
			getPaymentMethodDataDto.setCreatedDate(r.getCreatedDate());
			getPaymentMethodDataDto.setUpdateBy(r.getUpdateBy());
			getPaymentMethodDataDto.setUpdateDate(r.getUpdateDate());
			getPaymentMethodDataDto.setIsActive(r.getIsActive());
			getPaymentMethodDataDto.setVersion(r.getVersion());
			listGetPaymentMethodDataDto.add(getPaymentMethodDataDto);
		});
		getAllPaymentMethodResDto.setData(listGetPaymentMethodDataDto);
		return (T) getAllPaymentMethodResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Long id) throws Exception {
		GetByPaymentMethodIdResDto getByPaymentMethodIdResDto = new GetByPaymentMethodIdResDto();
		GetPaymentMethodDataDto getPaymentMethodDataDto = new GetPaymentMethodDataDto();
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod = paymentMethodDao.getById(id);
		getPaymentMethodDataDto.setCode(paymentMethod.getPaymentCode());
		getPaymentMethodDataDto.setNames(paymentMethod.getPaymentName());
		getPaymentMethodDataDto.setCreatedBy(paymentMethod.getId());
		getPaymentMethodDataDto.setCreatedDate(paymentMethod.getCreatedDate());
		getPaymentMethodDataDto.setUpdateBy(paymentMethod.getUpdateBy());
		getPaymentMethodDataDto.setUpdateDate(paymentMethod.getUpdateDate());
		getPaymentMethodDataDto.setIsActive(paymentMethod.getIsActive());
		getPaymentMethodDataDto.setVersion(paymentMethod.getVersion());
		getByPaymentMethodIdResDto.setData(getPaymentMethodDataDto);
		return (T) getByPaymentMethodIdResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T insert(R data) throws Exception {
		InsertPaymentMethodResDto insertPaymentMethodResDto = new InsertPaymentMethodResDto();
		;
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		InsertPaymentMethodReqDto insertPaymentMethodReqDto = (InsertPaymentMethodReqDto) data;
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setPaymentCode(insertPaymentMethodReqDto.getCode());
		paymentMethod.setPaymentName(insertPaymentMethodReqDto.getNames());
		paymentMethod.setCreatedBy(insertPaymentMethodReqDto.getCreatedBy());
		paymentMethod.setIsActive(insertPaymentMethodReqDto.getIsActive());
		Long id = paymentMethodDao.insert(paymentMethod).getId();
		insertResDataDto.setId(id);
		insertPaymentMethodResDto.setData(insertResDataDto);
		insertPaymentMethodResDto.setMessage(Message.SUCCESS.getNames());
		return (T) insertPaymentMethodResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T, R> T update(R data) throws Exception {
		UpdatePaymentMethodResDto updatePaymentMethodResDto = new UpdatePaymentMethodResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		UpdatePaymentMethodReqDto updatePaymentMethodReqDto = (UpdatePaymentMethodReqDto) data;
		PaymentMethod paymentMethod = paymentMethodDao.getById(updatePaymentMethodReqDto.getId());
		paymentMethod.setPaymentName(updatePaymentMethodReqDto.getNames());
		paymentMethod.setUpdateBy(updatePaymentMethodReqDto.getUpdateBy());
		paymentMethod.setIsActive(updatePaymentMethodReqDto.getIsActive());
		paymentMethod.setVersion(updatePaymentMethodReqDto.getVersion());
		updateResDataDto.setVersion(paymentMethodDao.update(paymentMethod).getVersion());
		updatePaymentMethodResDto.setData(updateResDataDto);
		updatePaymentMethodResDto.setMessage(Message.SUCCESS.getNames());
		return (T) updatePaymentMethodResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T> T deleteById(Long id) throws Exception {
		DeletePaymentMethodResDto deletePaymentMethodResDto = new DeletePaymentMethodResDto();
		paymentMethodDao.deleteById(id);
		deletePaymentMethodResDto.setMessage(Message.SUCCESS.getNames());
		return (T) deletePaymentMethodResDto;

	}
}
