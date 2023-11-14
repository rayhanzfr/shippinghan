package com.rayhan.shippinghan.dao.hibernate.nativequery;

import com.rayhan.shippinghan.helper.DeliveryHelper;
import com.rayhan.shippinghan.model.Profile;
import com.rayhan.shippinghan.model.Shipment;
import com.rayhan.shippinghan.model.ShipmentDelivery;
import com.rayhan.shippinghan.model.Status;
import com.rayhan.shippinghan.model.Users;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.ShipmentDeliveryDao;

@org.springframework.context.annotation.Profile("native")
@Repository(value = "shipmentDeliveryDaoNative")
public class ShipmentDeliveryDaoHibernateNativeImpl extends BaseDaoImpl implements ShipmentDeliveryDao{

	@Override
	public List<ShipmentDelivery> getAll() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT sd.id,sd.id_shipment,sd.receiver_name,sd.id_status,s.status_name,sd.time_delivered, sd.created_by " );
		sql.append(" FROM shipment_delivery sd ");
		sql.append(" INNER JOIN status s ON s.id = sd.id_status ");
		sql.append(" ORDER BY sd.created_date DESC  ");
		List<?> result = em.createNativeQuery(sql.toString()).getResultList();
		List<ShipmentDelivery> listShipmentDelivery = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			ShipmentDelivery shipmentDelivery = new ShipmentDelivery();
			Shipment shipment = new Shipment();
			Status status = new Status();
			shipmentDelivery.setId(Long.valueOf(objArr[0].toString()));
			shipment.setId(Long.valueOf(objArr[1].toString()));
			status.setId(Long.valueOf(objArr[2].toString()));
			status.setStatusName((objArr[3].toString()));
			shipmentDelivery.setShipment(shipment);
			shipmentDelivery.setStatus(status);
			shipmentDelivery.setReceiverName(objArr[4].toString());
			shipmentDelivery.setTimeDelivered((LocalDateTime)objArr[5]);
			shipmentDelivery.setCreatedBy(Long.valueOf(objArr[6].toString()));
			listShipmentDelivery.add(shipmentDelivery);
		});
		return null;
	}

	@Override
	public ShipmentDelivery getById(Long id) throws Exception {
		ShipmentDelivery shipmentDelivery = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT id_shipment,receiver_name,id_status,time_delivered ");
			sql.append(" FROM shipment_delivery ");
			sql.append(" WHERE id = :id ");
			Object result = em.createNativeQuery(sql.toString())
					.setParameter("id", id)
					.getSingleResult();
			if(result!=null) {
				shipmentDelivery = new ShipmentDelivery();
				Object[] objArr = (Object[]) result;
				Shipment shipment = new Shipment();
				Status status = new Status();
				shipment.setId(Long.valueOf(objArr[0].toString()));
				shipmentDelivery.setReceiverName(objArr[1].toString());
				shipmentDelivery.setShipment(shipment);
				status.setId(Long.valueOf(objArr[2].toString()));
				shipmentDelivery.setStatus(status);
				shipmentDelivery.setTimeDelivered((LocalDateTime)objArr[3]);
			}
			return shipmentDelivery;
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return shipmentDelivery;
	}

	@Override
	public ShipmentDelivery insert(ShipmentDelivery data) throws Exception {
		
		em.persist(data);
		
		return data;
	}

	@Override
	public ShipmentDelivery update(ShipmentDelivery data) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE shipment_delivery SET receiver_name = :receiver_name, id_status = :id_status, time_delivered = :time_delivered, ");
		sql.append(" update_by = :update_by, update_date= :update_date, isactive = :isactive WHERE id = :id ");
		
		em.createNativeQuery(sql.toString())
			.setParameter("receiver_name", data.getReceiverName())
			.setParameter("id_status", data.getStatus().getId())
			.setParameter("time_delivered", data.getTimeDelivered())
			.setParameter("update_by", data.getUpdateBy())
			.setParameter("update_date", data.getUpdateDate())
			.setParameter("isactive", data.getIsActive())
			.setParameter("id", data.getId())
			.executeUpdate();
		
		return data;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = "DELETE FROM shipment_delivery WHERE id =:id ";
		
		int result = em.createNativeQuery(sql)
				.setParameter("id", id)
				.executeUpdate();
		
		return result > 0;
	}

	@Override
	public List<DeliveryHelper> getByCreatedBy(Long createdBy) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT sd.id,sd.id_shipment,ship.shipment_number,sd.receiver_name,sd.id_status,s.status_name,sd.time_delivered,sd.created_by,p.firstnames");
		sql.append(" FROM shipment_delivery sd ");
		sql.append(" INNER JOIN status s ON s.id = sd.id_status ");
		sql.append(" INNER JOIN shipment ship ON ship.id = sd.id_shipment ");
		sql.append(" INNER JOIN users u ON sd.created_by = u.id ");
		sql.append(" INNER JOIN profile p ON u.id = p.id_user ");
		sql.append(" WHERE sd.created_by = :created_by ");
		sql.append(" ORDER BY sd.created_date DESC ");
		List<?> result = em.createNativeQuery(sql.toString())
				.setParameter("created_by", createdBy)
				.getResultList();
		List<DeliveryHelper> listDeliveryHelper = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			DeliveryHelper courierHelper = new DeliveryHelper();
			ShipmentDelivery shipmentDelivery = new ShipmentDelivery();
			Shipment shipment = new Shipment();
			Status status = new Status();
			Users users = new Users();
			Profile profile = new Profile();
			shipmentDelivery.setId(Long.valueOf(objArr[0].toString()));
			shipment.setId(Long.valueOf(objArr[1].toString()));
			shipment.setShipmentNumber(objArr[2].toString());
			shipmentDelivery.setReceiverName(objArr[3].toString());
			status.setId(Long.valueOf(objArr[4].toString()));
			status.setStatusName(objArr[5].toString());
			shipmentDelivery.setShipment(shipment);
			shipmentDelivery.setStatus(status);
			if(objArr[6]!=null) {
//				shipmentDelivery.setTimeDelivered((LocalDateTime)objArr[6]);
				shipmentDelivery.setTimeDelivered(((Timestamp)objArr[6]).toLocalDateTime());
			}
			users.setId(Long.valueOf(objArr[7].toString()));
			profile.setUsers(users);
			profile.setFirstNames(objArr[8].toString());
			courierHelper.setProfile(profile);
			courierHelper.setShipmentDelivery(shipmentDelivery);
			listDeliveryHelper.add(courierHelper);
		});
		return listDeliveryHelper;
	}

	@Override
	public List<ShipmentDelivery> getByCreatedByAndStatus(Long createdBy, Long idStatus) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT sd.id,sd.id_shipment,ship.shipment_number,sd.receiver_name,sd.id_status,s.status_name ");
		sql.append(" FROM shipment_delivery sd ");
		sql.append(" INNER JOIN status s ON s.id = sd.id_status ");
		sql.append(" INNER JOIN shipment ship ON ship.id = sd.id_shipment");
		sql.append(" WHERE sd.created_by = :created_by AND sd.id_status =:id_status ");
		sql.append(" ORDER BY sd.created_date DESC ");
		List<?> result = em.createNativeQuery(sql.toString())
				.setParameter("created_by", createdBy)
				.setParameter("id_status", idStatus)
				.getResultList();
		List<ShipmentDelivery> listShipmentDelivery = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			ShipmentDelivery shipmentDelivery = new ShipmentDelivery();
			Shipment shipment = new Shipment();
			Status status = new Status();
			shipmentDelivery.setId(Long.valueOf(objArr[0].toString()));
			shipment.setId(Long.valueOf(objArr[1].toString()));
			shipment.setShipmentNumber(objArr[2].toString());
			shipmentDelivery.setReceiverName(objArr[3].toString());
			status.setId(Long.valueOf(objArr[4].toString()));
			status.setStatusName(objArr[5].toString());
			shipmentDelivery.setShipment(shipment);
			shipmentDelivery.setStatus(status);
			listShipmentDelivery.add(shipmentDelivery);
		});
		return listShipmentDelivery;
	}

}
