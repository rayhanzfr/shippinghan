package com.rayhan.shippinghan.dao.hibernate.hql;

import com.rayhan.shippinghan.helper.DeliveryHelper;
import com.rayhan.shippinghan.model.Profile;
import com.rayhan.shippinghan.model.Shipment;
import com.rayhan.shippinghan.model.ShipmentDelivery;
import com.rayhan.shippinghan.model.Status;
import com.rayhan.shippinghan.model.Users;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.ShipmentDeliveryDao;

@org.springframework.context.annotation.Profile("hql")
@Repository
public class ShipmentDeliveryDaoHibernateHQLImpl extends BaseDaoImpl implements ShipmentDeliveryDao{

	@Override
	public List<ShipmentDelivery> getAll() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT sd ");
		sql.append(" FROM ShipmentDelivery as sd ");
		sql.append(" INNER JOIN FETCH sd.shipment ");
		sql.append(" INNER JOIN FETCH sd.status ");
		
		List<ShipmentDelivery> result = em.createQuery(sql.toString(),ShipmentDelivery.class).getResultList();
		return result;
	}

	@Override
	public ShipmentDelivery getById(Long id) throws Exception {
		ShipmentDelivery shipmentDelivery = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT sd ");
			sql.append(" FROM ShipmentDelivery as sd ");
			sql.append(" INNER JOIN FETCH sd.shipment ");
			sql.append(" INNER JOIN FETCH sd.status ");
			sql.append(" WHERE sd.id = :id ");
			shipmentDelivery =  em.createQuery(sql.toString(),ShipmentDelivery.class)
					.setParameter("id", id)
					.getSingleResult();
			em.detach(shipmentDelivery);
			return shipmentDelivery;
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return shipmentDelivery;
	}

	@Override
	public ShipmentDelivery insert(ShipmentDelivery data) throws Exception {
		return insertData(data);
	}

	@Override
	public ShipmentDelivery update(ShipmentDelivery data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = "DELETE FROM shipment_delivery WHERE id =:id ";
		
		int result = em.createQuery(sql)
				.setParameter("id", id)
				.executeUpdate();
		
		return result > 0;
	}

	@Override
	public List<DeliveryHelper> getByCreatedBy(Long createdBy) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT sd.id,s.id,s.shipmentNumber,sd.receiverName,stat.id,stat.statusName,sd.timeDelivered,sd.createdBy,p.firstNames");
		sql.append(" FROM ShipmentDelivery as sd ");
		sql.append(" INNER JOIN Shipment s ON s.id = sd.shipment.id ");
		sql.append(" INNER JOIN Status stat ON stat.id = sd.status.id ");
		sql.append(" INNER JOIN Profile as p ON p.users.id = sd.createdBy ");
		sql.append(" WHERE sd.createdBy = :createdBy ");
		sql.append(" ORDER BY sd.createdDate DESC ");
		List<?> result = em.createQuery(sql.toString())
				.setParameter("createdBy", createdBy)
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
				shipmentDelivery.setTimeDelivered((LocalDateTime)objArr[6]);
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
		sql.append(" SELECT sd.id,sd.shipment.id,sd.shipment.shipmentNumber,sd.receiverName,sd.status.id,sd.status.statusName ");
		sql.append(" FROM ShipmentDelivery as sd ");
		sql.append(" INNER JOIN Shipment s ON s.id = sd.shipment.id ");
		sql.append(" INNER JOIN Status stat ON stat.id = sd.status.id ");
		sql.append(" WHERE sd.createdBy = :createdBy AND sd.status.id =:status ");
		sql.append(" ORDER BY sd.createdDate DESC ");
		List<?> result = em.createQuery(sql.toString())
				.setParameter("createdBy", createdBy)
				.setParameter("status", idStatus)
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
