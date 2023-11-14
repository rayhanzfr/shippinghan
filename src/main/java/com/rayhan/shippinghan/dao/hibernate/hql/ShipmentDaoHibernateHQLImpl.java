package com.rayhan.shippinghan.dao.hibernate.hql;

import com.rayhan.shippinghan.model.Shipment;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.ShipmentDao;

@Profile("hql")
@Repository
public class ShipmentDaoHibernateHQLImpl extends BaseDaoImpl implements ShipmentDao {

	@Override
	public List<Shipment> getAll(Long branchId, String statusCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT s ");
		sql.append(" FROM Shipment as s");
		sql.append(" INNER JOIN FETCH s.status ");
		sql.append(" INNER JOIN FETCH s.paymentMethod ");
		sql.append(" INNER JOIN FETCH s.serviceType ");
		sql.append(" INNER JOIN FETCH s.receiverDestination ");
		if (branchId != null && statusCode != null) {
			sql.append(" WHERE s.status.statusCode = '" + statusCode + "' AND s.receiverDestination.id = '" + branchId + "'");
		} else if (branchId!= null && statusCode == null) {
			sql.append(" WHERE s.receiverDestination.id = '" + branchId + "'");
		}else if(branchId== null && statusCode != null){
			sql.append(" WHERE s.status.statusCode = '" + statusCode +"'");
		}
		List<Shipment> result = em.createQuery(sql.toString(), Shipment.class).getResultList();
		return result;
	}

	@Override
	public Shipment getById(Long id) throws Exception {
		try {
			Shipment shipment = em.find(Shipment.class, id);
			em.detach(shipment);
			return shipment;
		} catch (NoResultException e) {
			throw new NoResultException();
		}catch(NonUniqueResultException e) {
			throw new NonUniqueResultException();
		}
	}

	@Override
	public Shipment insert(Shipment data) throws Exception {
		return insertData(data);
	}

	@Override
	public Shipment update(Shipment data) throws Exception {
		return updateData(data);
	}
	
	@Override
	public Integer countData() throws Exception {
		String sql = "SELECT COUNT(s.id) FROM Shipment s ";
		Object result = em.createQuery(sql).getSingleResult();
		BigInteger results = new BigInteger(result.toString());
		return results.intValue();
	}


}
