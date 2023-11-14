package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.constant.Message;
import com.rayhan.shippinghan.constant.StatusType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayhan.shippinghan.dao.BranchDao;
import com.rayhan.shippinghan.dao.CategoryDao;
import com.rayhan.shippinghan.dao.PaymentMethodDao;
import com.rayhan.shippinghan.dao.ServiceTypeDao;
import com.rayhan.shippinghan.dao.ShipmentDao;
import com.rayhan.shippinghan.dao.ShipmentDetailDao;
import com.rayhan.shippinghan.dao.StatusDao;
import com.rayhan.shippinghan.dto.InsertResDataDto;
import com.rayhan.shippinghan.dto.UpdateResDataDto;
import com.rayhan.shippinghan.dto.shipment.GetAllShipmentResDto;
import com.rayhan.shippinghan.dto.shipment.GetByShipmentIdResDto;
import com.rayhan.shippinghan.dto.shipment.GetShipmentDataDto;
import com.rayhan.shippinghan.dto.shipment.InsertFullShipmentReqDto;
import com.rayhan.shippinghan.dto.shipment.InsertFullShipmentResDto;
import com.rayhan.shippinghan.dto.shipment.InsertShipmentDetailReqDto;
import com.rayhan.shippinghan.dto.shipment.InsertShipmentDetailResDto;
import com.rayhan.shippinghan.dto.shipment.InsertShipmentReqDto;
import com.rayhan.shippinghan.dto.shipment.InsertShipmentResDto;
import com.rayhan.shippinghan.dto.shipment.UpdateStatusShipmentReqDto;
import com.rayhan.shippinghan.dto.shipment.UpdateStatusShipmentResDto;
import com.rayhan.shippinghan.model.Branch;
import com.rayhan.shippinghan.model.Category;
import com.rayhan.shippinghan.model.PaymentMethod;
import com.rayhan.shippinghan.model.ServiceType;
import com.rayhan.shippinghan.model.Shipment;
import com.rayhan.shippinghan.model.ShipmentDetail;
import com.rayhan.shippinghan.model.Status;

@Service
public class ShipmentServiceImpl extends BaseServiceImpl implements ShipmentService {
	private ShipmentDao shipmentDao;
	private ShipmentDetailDao shipmentDetailDao;
	private StatusDao statusDao;
	private PaymentMethodDao paymentMethodDao;
	private ServiceTypeDao serviceTypeDao;
	private BranchDao branchDao;
	private CategoryDao categoryDao;

	@Autowired
	public void setShipmentDao(ShipmentDao shipmentDao) {
		this.shipmentDao = shipmentDao;
	}

	@Autowired
	public void setShipmentDetailDao(ShipmentDetailDao shipmentDetailDao) {
		this.shipmentDetailDao = shipmentDetailDao;
	}

	@Autowired
	public void setPaymentMethodDao(PaymentMethodDao paymentMethodDao) {
		this.paymentMethodDao = paymentMethodDao;
	}

	@Autowired
	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}

	@Autowired
	public void setServiceTypeDao(ServiceTypeDao serviceTypeDao) {
		this.serviceTypeDao = serviceTypeDao;
	}

	@Autowired
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public GetAllShipmentResDto getAll(Long branchId, String statusCode) throws Exception {
		GetAllShipmentResDto getAllShipmentResDto = new GetAllShipmentResDto();
		List<GetShipmentDataDto> listGetShipmentDataDto = new ArrayList<>();
		List<Shipment> listShipment = shipmentDao.getAll(branchId, statusCode);
		listShipment.forEach(r -> {
			GetShipmentDataDto getShipmentDataDto = new GetShipmentDataDto();
			getShipmentDataDto.setId(r.getId());
			getShipmentDataDto.setStatusId(r.getStatus().getId());
			getShipmentDataDto.setStatusName(r.getStatus().getStatusName());
			getShipmentDataDto.setPaymentMethodId(r.getServiceType().getId());
			getShipmentDataDto.setServiceTypeId(r.getServiceType().getId());
			getShipmentDataDto.setBranchId(r.getReceiverDestination().getId());
			getShipmentDataDto.setId(r.getId());
			getShipmentDataDto.setShipmentNumber(r.getShipmentNumber());
			getShipmentDataDto.setSenderNames(r.getSenderName());
			getShipmentDataDto.setReceiverNames(r.getReceiverName());
			getShipmentDataDto.setPrice(r.getPrice());
			getShipmentDataDto.setAddress(r.getAddress());
			getShipmentDataDto.setPhoneNumber(r.getPhoneNumber());
			getShipmentDataDto.setShippingDate(r.getShippingDate().toString());
			getShipmentDataDto.setCreatedBy(r.getId());
			getShipmentDataDto.setCreatedDate(r.getCreatedDate());
			getShipmentDataDto.setUpdateBy(r.getUpdateBy());
			getShipmentDataDto.setUpdateDate(r.getUpdateDate());
			getShipmentDataDto.setIsActive(r.getIsActive());
			getShipmentDataDto.setVersion(r.getVersion());
			listGetShipmentDataDto.add(getShipmentDataDto);
		});
		getAllShipmentResDto.setData(listGetShipmentDataDto);
		return getAllShipmentResDto;
	}

	@Override
	public GetByShipmentIdResDto getById(Long id) throws Exception {
		GetByShipmentIdResDto getByShipmentIdResDto = new GetByShipmentIdResDto();
		GetShipmentDataDto getShipmentDataDto = new GetShipmentDataDto();
		Status status = new Status();
		PaymentMethod paymentMethod = new PaymentMethod();
		ServiceType serviceType = new ServiceType();
		Branch branch = new Branch();
		Shipment shipment = shipmentDao.getById(id);
		status.setId(shipment.getStatus().getId());
		paymentMethod.setId(shipment.getServiceType().getId());
		serviceType.setId(shipment.getServiceType().getId());
		branch.setId(shipment.getReceiverDestination().getId());
		getShipmentDataDto.setId(id);
		getShipmentDataDto.setStatusId(status.getId());
		getShipmentDataDto.setPaymentMethodId(paymentMethod.getId());
		getShipmentDataDto.setServiceTypeId(serviceType.getId());
		getShipmentDataDto.setShipmentNumber(shipment.getShipmentNumber());
		getShipmentDataDto.setSenderNames(shipment.getSenderName());
		getShipmentDataDto.setReceiverNames(shipment.getReceiverName());
		getShipmentDataDto.setPrice(shipment.getPrice());
		getShipmentDataDto.setAddress(shipment.getAddress());
		getShipmentDataDto.setPhoneNumber(shipment.getPhoneNumber());
		getShipmentDataDto.setCreatedBy(shipment.getId());
		getShipmentDataDto.setCreatedDate(shipment.getCreatedDate());
		getShipmentDataDto.setUpdateBy(shipment.getUpdateBy());
		getShipmentDataDto.setUpdateDate(shipment.getUpdateDate());
		getShipmentDataDto.setIsActive(shipment.getIsActive());
		getShipmentDataDto.setVersion(shipment.getVersion());
		getByShipmentIdResDto.setData(getShipmentDataDto);
		return getByShipmentIdResDto;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertFullShipmentResDto insert(InsertFullShipmentReqDto data) throws Exception {
		InsertFullShipmentResDto insertFullShipmentResDto = new InsertFullShipmentResDto();
		InsertResDataDto headerId = new InsertResDataDto();
		InsertFullShipmentReqDto insertFullShipmentReqDto = (InsertFullShipmentReqDto) data;
		InsertShipmentReqDto insertShipmentReqDto = insertFullShipmentReqDto.getInsertShipmentReqDto();
		List<InsertShipmentDetailReqDto> listInsertShipmentDetailReqDto = insertFullShipmentReqDto
				.getListInsertShipmentDetailReqDto();
		List<InsertShipmentDetailResDto> listDetailId = new ArrayList<>();
		InsertShipmentResDto header = new InsertShipmentResDto();
		Status status = statusDao.getByStatusCode(StatusType.NEW.getStatusCode());
		PaymentMethod paymentMethod = paymentMethodDao.getById(insertShipmentReqDto.getPaymentMethodId());
		ServiceType serviceType = serviceTypeDao.getById(insertShipmentReqDto.getServiceTypeId());
		Branch branch = branchDao.getById(insertShipmentReqDto.getBranchId());
		Shipment shipment = new Shipment();
		StringBuilder shipmentNumbers = new StringBuilder();
		shipmentNumbers.append("SHAN000");
		shipmentNumbers.append((countData() + 1));
		shipment.setStatus(status);
		shipment.setPaymentMethod(paymentMethod);
		shipment.setServiceType(serviceType);
		shipment.setReceiverDestination(branch);
		shipment.setShipmentNumber(shipmentNumbers.toString());
		shipment.setSenderName(insertShipmentReqDto.getSenderNames());
		shipment.setReceiverName(insertShipmentReqDto.getReceiverNames());
		shipment.setShippingDate(LocalDateTime.now());
		shipment.setPrice(insertShipmentReqDto.getPrice());
		shipment.setPhoneNumber(insertShipmentReqDto.getPhoneNumber());
		shipment.setCreatedBy(getIdAuth());
		shipment.setIsActive(true);
		final Shipment shipmentFinal = shipmentDao.insert(shipment);
		listInsertShipmentDetailReqDto.forEach(i -> {
			try {
				InsertResDataDto detailId = new InsertResDataDto();
				InsertShipmentDetailResDto detailRes = new InsertShipmentDetailResDto();
				ShipmentDetail shipmentDetail = new ShipmentDetail();
				Category category = new Category();
				category = categoryDao.getById(i.getCategoryId());
				shipmentDetail.setShipment(shipmentFinal);
				shipmentDetail.setCategory(category);
				shipmentDetail.setItemName(i.getItemNames());
				shipmentDetail.setWeight(i.getWeight());
				shipmentDetail.setQuantity(i.getQuantity());
				shipmentDetail.setCreatedBy(getIdAuth());
				shipmentDetail.setIsActive(true);
				shipmentDetail = shipmentDetailDao.insert(shipmentDetail);
				detailId.setId(shipmentDetail.getId());
				detailRes.setInsertResDataDto(detailId);
				listDetailId.add(detailRes);
			} catch (Exception e) {
				throw new RuntimeException();
			}
		});
		headerId.setId(shipment.getId());
		header.setHeader(headerId);
		header.setListDetail(listDetailId);
		insertFullShipmentResDto.setData(header);
		insertFullShipmentResDto.setMessage(Message.SUCCESS.getNames());
		return insertFullShipmentResDto;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public UpdateStatusShipmentResDto update(UpdateStatusShipmentReqDto data) throws Exception {
		UpdateStatusShipmentResDto updateStatusShipmentResDto = new UpdateStatusShipmentResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		UpdateStatusShipmentReqDto updateStatusShipmentReqDto = (UpdateStatusShipmentReqDto) data;
		Status status = statusDao.getById(updateStatusShipmentReqDto.getStatusId());
		Shipment shipment = shipmentDao.getById(updateStatusShipmentReqDto.getId());
		shipment.setStatus(status);
		shipment.setUpdateBy(getIdAuth());
		shipment.setVersion(updateStatusShipmentReqDto.getVersion());
		shipment = shipmentDao.update(shipment);
		updateResDataDto.setVersion(shipment.getVersion());
		updateStatusShipmentResDto.setData(updateResDataDto);
		return updateStatusShipmentResDto;
	}

	@Override
	public Integer countData() throws Exception {
		return shipmentDao.countData();
	}

}
