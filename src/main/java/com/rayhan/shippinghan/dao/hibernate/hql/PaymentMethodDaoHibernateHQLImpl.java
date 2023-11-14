package com.rayhan.shippinghan.dao.hibernate.hql;

import com.rayhan.shippinghan.model.PaymentMethod;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.PaymentMethodDao;

@Profile("hql")
@Repository
public class PaymentMethodDaoHibernateHQLImpl extends BaseDaoImpl implements PaymentMethodDao{
	@Override
	public List<PaymentMethod> getAll() throws Exception {
		String sql = "SELECT pm FROM PaymentMethod as pm";
		List<PaymentMethod> result = em.createQuery(sql,PaymentMethod.class).getResultList();
		return result;
	}

	@Override
	public PaymentMethod getById(Long id) throws Exception {
		PaymentMethod paymentMethod = null;
		try {
			paymentMethod =  em.find(PaymentMethod.class, id);
			em.detach(paymentMethod);
			return paymentMethod;
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return paymentMethod;
	}

	@Override
	public PaymentMethod insert(PaymentMethod data) throws Exception {
		return insertData(data);
	}

	@Override
	public PaymentMethod update(PaymentMethod data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql="DELETE FROM PaymentMethod WHERE id = :id";
		
		int result = em.createQuery(sql)
			.setParameter("id", id)
			.executeUpdate();
		
		return result>0;
	}
}
