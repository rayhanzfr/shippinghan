package com.rayhan.shippinghan.dao.hibernate.nativequery;

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

@Profile("native")
@Repository(value = "shipmentDetailDaoNative")
public class ShipmentDetailDaoHibernateNativeImpl extends BaseDaoImpl implements ShipmentDetailDao{

	@Override
	public List<ShipmentDetail> getAll() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT id_shipment,id_cat,item_name,weight, quantity ");
		sql.append(" FROM shipment_detail sd ");
		sql.append(" INNER JOIN status s ON s.id = sd.id_status ");
		sql.append(" ORDER BY sd.created_date DESC ");
		List<?> result = em.createNativeQuery(sql.toString()).getResultList();
		List<ShipmentDetail> listShipmentDetail = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			ShipmentDetail shipmentDetail = new ShipmentDetail();
			Shipment shipment = new Shipment();
			Category category = new Category();
			shipment.setId(Long.valueOf(objArr[0].toString()));
			category.setId(Long.valueOf(objArr[1].toString()));
			shipmentDetail.setShipment(shipment);
			shipmentDetail.setCategory(category);
			shipmentDetail.setItemName(objArr[2].toString());
			long weight = (long) objArr[3];
			shipmentDetail.setWeight((int) weight);
			long quantity = (long) objArr[4];
			shipmentDetail.setQuantity((int) quantity);
			listShipmentDetail.add(shipmentDetail);
		});
		return listShipmentDetail;
	}

	@Override
	public ShipmentDetail getById(Long id) throws Exception {
		ShipmentDetail shipmentDetail = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT id_shipment,id_cat,item_name,weight, quantity ");
			sql.append(" FROM shipment_detail sd ");
			sql.append(" WHERE sd.id = :id ");
			Object result =  em.createNativeQuery(sql.toString())
					.setParameter("id", id)
					.getSingleResult();
			if(result!=null) {
				shipmentDetail = new ShipmentDetail();
				Object[] objArr = (Object[]) result;
				Shipment shipment = new Shipment();
				Category category = new Category();
				shipment.setId(Long.valueOf(objArr[0].toString()));
				category.setId(Long.valueOf(objArr[1].toString()));
				shipmentDetail.setShipment(shipment);
				shipmentDetail.setCategory(category);
				shipmentDetail.setItemName(objArr[2].toString());
				long weight = (long) objArr[3];
				shipmentDetail.setWeight((int) weight);
				long quantity = (long) objArr[4];
				shipmentDetail.setQuantity((int) quantity);
			}
			return shipmentDetail;
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ShipmentDetail insert(ShipmentDetail data) throws Exception {
		
		em.persist(data);
		
		return data;
	}

	@Override
	public ShipmentDetail update(ShipmentDetail data) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE shipment_detail ");
		sql.append(" SET id_shipment = :id_shipment, item_name = :item_name ,weight = :weight, quantity = :quantity, ");
		sql.append(" update_by = :update_by, update_date= :update_date, isactive = :isactive ");
		sql.append(" WHERE id = :id ");
		
		em.createNativeQuery(sql.toString())
			.setParameter("id_status", data.getShipment().getId())
			.setParameter("item_name", data.getItemName())
			.setParameter("weight", data.getWeight())
			.setParameter("quantity", data.getWeight())
			.setParameter("update_by", data.getUpdateBy())
			.setParameter("update_date", data.getUpdateDate())
			.setParameter("isactive", data.getIsActive())
			.setParameter("id", data.getId())
			.executeUpdate();
		
		return data;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = "DELETE FROM shipment_detail WHERE id =:id ";
		
		int result = em.createNativeQuery(sql)
				.setParameter("id", id)
				.executeUpdate();
		
		return result > 0;
	}

	@Override
	public List<ShipmentDetail> getByShipmentId(Long shipmentId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT sd.id_shipment,c.names,sd.item_name,sd.weight, sd.quantity ");
		sql.append(" FROM shipment_detail sd ");
		sql.append(" INNER JOIN categories c ON c.id = sd.id_cat ");
		sql.append(" WHERE id_shipment = :id_shipment ");
		sql.append(" ORDER BY sd.created_date DESC ");
		List<?> result = em.createNativeQuery(sql.toString())
				.setParameter("id_shipment", shipmentId)
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
