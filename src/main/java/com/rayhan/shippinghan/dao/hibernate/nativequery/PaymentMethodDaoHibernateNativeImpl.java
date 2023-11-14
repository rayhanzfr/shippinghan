package com.rayhan.shippinghan.dao.hibernate.nativequery;

import com.rayhan.shippinghan.model.PaymentMethod;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.PaymentMethodDao;

@Profile("native")
@Repository(value = "paymentMethodDaoNative")
public class PaymentMethodDaoHibernateNativeImpl extends BaseDaoImpl implements PaymentMethodDao{

	@Override
	public List<PaymentMethod> getAll() throws Exception {
		String sql = "SELECT id,payment_name,payment_code,created_by,created_date,isactive,update_by,update_date,version FROM payment_method";
		List<?> result = em.createNativeQuery(sql.toString()).getResultList();
		List<PaymentMethod> listPaymentMethod = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			PaymentMethod paymentMethod = new PaymentMethod();
			paymentMethod.setId(Long.valueOf(objArr[0].toString()));
			paymentMethod.setPaymentName(objArr[1].toString());
			paymentMethod.setPaymentCode(objArr[2].toString());
			listPaymentMethod.add(paymentMethod);
			
		});
		return listPaymentMethod;
	}

	@Override
	public PaymentMethod getById(Long id) throws Exception {
		PaymentMethod paymentMethod = null;
		try {
			String sql = "SELECT id,payment_name,payment_code,created_by,created_date,isactive,update_by,update_date,version FROM payment_method WHERE id =:id ";
			Object result =  em.createNativeQuery(sql)
					.setParameter("id", id)
					.getSingleResult();
			if(result!=null) {
				paymentMethod = new PaymentMethod();
				Object[] objArr = (Object[]) result;
				paymentMethod.setId(Long.valueOf(objArr[0].toString()));
				paymentMethod.setPaymentName(objArr[1].toString());
				paymentMethod.setPaymentCode(objArr[2].toString());
				paymentMethod.setCreatedBy(Long.valueOf(objArr[3].toString()));
				paymentMethod.setCreatedDate(((Timestamp)objArr[4]).toLocalDateTime());
				paymentMethod.setVersion(Long.valueOf(objArr[8].toString()).intValue());
			}
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
		String sql = "DELETE FROM payment_method WHERE id = :id ";
		
		int result = em.createNativeQuery(sql)
				.setParameter("id", id)
				.executeUpdate();
		
		return result>0;
	}

}
