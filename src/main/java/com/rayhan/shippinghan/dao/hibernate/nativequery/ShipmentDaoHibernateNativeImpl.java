package com.rayhan.shippinghan.dao.hibernate.nativequery;

import com.rayhan.shippinghan.model.Branch;
import com.rayhan.shippinghan.model.PaymentMethod;
import com.rayhan.shippinghan.model.ServiceType;
import com.rayhan.shippinghan.model.Shipment;
import com.rayhan.shippinghan.model.Status;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.ShipmentDao;

@Profile("native")
@Repository(value = "shipmentDaoNative")
public class ShipmentDaoHibernateNativeImpl extends BaseDaoImpl implements ShipmentDao{

	@Override
	public List<Shipment> getAll(Long branchId,String statusCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT shipment.id,id_status,stat.status_name,id_payment,id_service_type, receiver_destination, b.branches_name, shipment_number,");
		sql.append(" sender_name, receiver_name, shipping_date, price");
		sql.append(" FROM shipment shipment");
		sql.append(" INNER JOIN status stat ON stat.id = shipment.id_status ");
		sql.append(" INNER JOIN branches b ON b.id = shipment.receiver_destination ");
		if(branchId!=null && statusCode!=null ) {
			sql.append(" WHERE shipment.id_status = '"+statusCode+"' AND shipment.receiver_destionation = '"+branchId+"'");
		}else if(statusCode==null){
			sql.append(" WHERE shipment.receiver_destionation = '"+branchId+"'");
		}
		sql.append(" ORDER BY shipment.created_date DESC ");
		List<?> result = em.createNativeQuery(sql.toString()).getResultList();
		List<Shipment> listShipment = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			Shipment shipment = new Shipment();
			Status status = new Status();
			PaymentMethod payment = new PaymentMethod();
			ServiceType serviceType = new ServiceType();
			Branch branch = new Branch();
			status.setId(Long.valueOf(objArr[0].toString()));
			status.setStatusName(objArr[1].toString());
			payment.setId(Long.valueOf(objArr[2].toString()));
			serviceType.setId(Long.valueOf(objArr[3].toString()));
			branch.setId(Long.valueOf(objArr[4].toString()));
			branch.setBranchesName(objArr[5].toString());
			shipment.setId(Long.valueOf(objArr[6].toString()));
			shipment.setStatus(status);
			shipment.setPaymentMethod(payment);
			shipment.setServiceType(serviceType);
			shipment.setReceiverDestination(branch);
			shipment.setShipmentNumber(objArr[7].toString());
			shipment.setSenderName(objArr[8].toString());
			shipment.setReceiverName(objArr[9].toString());
			shipment.setShippingDate(((Timestamp)objArr[10]).toLocalDateTime());
			shipment.setPrice(new BigDecimal(objArr[11].toString()));
			listShipment.add(shipment);
		});
		return listShipment;
	}

	@Override
	public Shipment getById(Long id) throws Exception {
		Shipment shipment = null;
		try {			
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT id_status,id_payment,id_service_type, receiver_destination, shipment_number,");
			sql.append(" sender_name, receiver_name, shipping_date, price ");
			sql.append(" FROM shipment ");
			sql.append(" WHERE id = :id ");
			Object result =  em.createNativeQuery(sql.toString())
					.setParameter("id", id)
					.getSingleResult();
			if(result!=null) {
				shipment = new Shipment();
				Object[] objArr = (Object[]) result;
				Status status = new Status();
				PaymentMethod payment = new PaymentMethod();
				ServiceType serviceType = new ServiceType();
				Branch branch = new Branch();
				status.setId(Long.valueOf(objArr[0].toString()));;
				payment.setId(Long.valueOf(objArr[1].toString()));
				serviceType.setId(Long.valueOf(objArr[2].toString()));
				branch.setId(Long.valueOf(objArr[3].toString()));
				shipment.setStatus(status);
				shipment.setPaymentMethod(payment);
				shipment.setServiceType(serviceType);
				shipment.setReceiverDestination(branch);
				shipment.setShipmentNumber(objArr[4].toString());
				shipment.setSenderName(objArr[5].toString());
				shipment.setReceiverName(objArr[6].toString());
				shipment.setShippingDate(((Timestamp)objArr[7]).toLocalDateTime());
			}
			return shipment;
		}catch(NoResultException e) {
			e.printStackTrace();
		}
		return shipment;
	}

	@Override
	public Shipment insert(Shipment data) throws Exception {
		
		em.persist(data);
		
		return data;
	}

	@Override
	public Shipment update(Shipment data) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE shipment SET id_status = :id_status, id_payment = :id_payment ,id_service_type = :id_service_type, receiver_destination = :receiver_destination, ");
		sql.append(" shipment_number = :shipment_number, sender_name = :sender_name, receiver_name = :receiver_name, shipping_date = :shipping_date, price = :price, phone_receiver = :phone_receiver, address = :address ");
		sql.append(" update_by = :update_by, update_date= :update_date, isactive = :isactive WHERE id = :id ");
		
		em.createNativeQuery(sql.toString())
			.setParameter("id_status", data.getStatus().getId())
			.setParameter("id_payment", data.getPaymentMethod().getId())
			.setParameter("id_service_type", data.getServiceType().getId())
			.setParameter("receiver_destination", data.getReceiverDestination())
			.setParameter("shipment_number", data.getShipmentNumber())
			.setParameter("sender_name", data.getSenderName())
			.setParameter("receiver_name", data.getReceiverName())
			.setParameter("shipping_date", data.getReceiverDestination())
			.setParameter("price", data.getPrice())
			.setParameter("phone_receiver", data.getPhoneNumber())
			.setParameter("address", data.getAddress())
			.setParameter("update_by", data.getUpdateBy())
			.setParameter("update_date", data.getUpdateDate())
			.setParameter("isactive", data.getIsActive())
			.setParameter("id", data.getId())
			.executeUpdate();
		
		return data;
	}

	@Override
	public Integer countData() throws Exception {
		String sql = "SELECT COUNT(id) AS count_id FROM shipment s ";
		Object result = em.createNativeQuery(sql).getSingleResult();
		BigInteger results =  new BigInteger(result.toString()); 
		return results.intValue();
	}

}
