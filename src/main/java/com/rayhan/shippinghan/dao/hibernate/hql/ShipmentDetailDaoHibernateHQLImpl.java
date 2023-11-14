package com.rayhan.shippinghan.dao.hibernate.hql;

import com.rayhan.shippinghan.model.Category;
import com.rayhan.shippinghan.model.Shipment;
import com.rayhan.shippinghan.model.ShipmentDetail;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.ShipmentDetailDao;

@Profile("hql")
@Repository
public class ShipmentDetailDaoHibernateHQLImpl extends BaseDaoImpl implements ShipmentDetailDao{

	@Override
	public List<ShipmentDetail> getAll() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT sd FROM ShipmentDetail as sd");
		sql.append(" INNER JOIN FETCH sd.shipment");
		sql.append(" INNER JOIN FETCH sd.category");
		List<ShipmentDetail> result = em.createQuery(sql.toString(),ShipmentDetail.class).getResultList();
		return result;
	}

	@Override
	public ShipmentDetail getById(Long id) throws Exception {
		ShipmentDetail shipmentDetail = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT sd FROM ShipmentDetail as sd ");
			sql.append(" INNER JOIN FETCH sd.shipment ");
			sql.append(" INNER JOIN FETCH sd.category ");
			sql.append(" WHERE sd.id = :id ");
			shipmentDetail =  em.createQuery(sql.toString(),ShipmentDetail.class)
					.setParameter("id", id)
					.getSingleResult();
			return shipmentDetail;
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return shipmentDetail;
	}

	@Override
	public ShipmentDetail insert(ShipmentDetail data) throws Exception {
		return insertData(data);
	}

	@Override
	public ShipmentDetail update(ShipmentDetail data) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE ShipmentDetail sd ");
		sql.append(" SET sd.shipment.id = :shipment, itemName = :itemName ,weight = :weight, quantity = :quantity, ");
		sql.append(" updateBy = :updateBy, updateDate= :updateDate, isActive = :isActive ");
		sql.append(" WHERE id = :id ");
		
		em.createQuery(sql.toString())
			.setParameter("shipment", data.getShipment().getId())
			.setParameter("itemName", data.getItemName())
			.setParameter("weight", data.getWeight())
			.setParameter("quantity", data.getWeight())
			.setParameter("updateBy", data.getUpdateBy())
			.setParameter("updateDate", data.getUpdateDate())
			.setParameter("isActive", data.getIsActive())
			.setParameter("id", data.getId())
			.executeUpdate();
		
		return data;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = "DELETE FROM ShipmentDetail WHERE id =:id ";
		
		int result = em.createQuery(sql)
				.setParameter("id", id)
				.executeUpdate();
		
		return result > 0;
	}

	@Override
	public List<ShipmentDetail> getByShipmentId(Long shipmentId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT s.id,c.names,sd.itemName,sd.weight, sd.quantity ");
		sql.append(" FROM ShipmentDetail sd ");
		sql.append(" INNER JOIN Shipment s ON s.id = sd.shipment.id");
		sql.append(" INNER JOIN Category c ON c.id = sd.category.id");
		sql.append(" WHERE sd.shipment.id = :shipment ");
		sql.append(" ORDER BY sd.createdDate DESC ");
		List<?> result = em.createQuery(sql.toString())
				.setParameter("shipment", shipmentId)
				.getResultList();
		List<ShipmentDetail> listShipmentDetail = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			ShipmentDetail shipmentDetail = new ShipmentDetail();
			Shipment shipment = new Shipment();
			Category category = new Category();
			shipment.setId(Long.valueOf(objArr[0].toString()));
			category.setNames(objArr[1].toString());
			shipmentDetail.setShipment(shipment);
			shipmentDetail.setCategory(category);
			shipmentDetail.setItemName(objArr[2].toString());
			Long weight = Long.valueOf(objArr[3].toString());
			shipmentDetail.setWeight(weight.intValue());
			Long quantity = Long.valueOf(objArr[4].toString());
			shipmentDetail.setQuantity(quantity.intValue());
			listShipmentDetail.add(shipmentDetail);
		});
		return listShipmentDetail;
	}

}
