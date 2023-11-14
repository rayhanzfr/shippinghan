package com.rayhan.shippinghan.dao.hibernate.nativequery;

import com.rayhan.shippinghan.model.Branch;
import com.rayhan.shippinghan.model.DefaultPrice;
import com.rayhan.shippinghan.model.ServiceType;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.DefaultPriceDao;

@Profile("native")
@Repository(value = "defaultPriceDaoNative")
public class DefaultPriceDaoHibernateNativeImpl extends BaseDaoImpl implements DefaultPriceDao{

	@Override
	public List<DefaultPrice> getAll() throws Exception {
		String sql = "SELECT id,id_branches,id_service_type,default_price_code,price,created_by,created_date FROM default_price";
		List<?> result = em.createNativeQuery(sql.toString()).getResultList();
		List<DefaultPrice> listDefaultPrice = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			DefaultPrice defaultPrice = new DefaultPrice();
			defaultPrice.setId(Long.valueOf(objArr[0].toString()));
			Branch branch = new Branch();
			branch.setId(Long.valueOf(objArr[1].toString()));
			ServiceType serviceType = new ServiceType();
			serviceType.setId(Long.valueOf(objArr[2].toString()));
			defaultPrice.setBranch(branch);
			defaultPrice.setServiceType(serviceType);
			defaultPrice.setDefaultPriceCode(objArr[3].toString());
			defaultPrice.setPrice(new BigDecimal(objArr[4].toString()));
			defaultPrice.setCreatedBy(Long.valueOf(objArr[5].toString()));
			defaultPrice.setCreatedDate(((Timestamp)objArr[6]).toLocalDateTime());
			listDefaultPrice.add(defaultPrice);
			
		});
		return listDefaultPrice;
	}

	@Override
	public DefaultPrice getById(Long id) throws Exception {
		DefaultPrice defaultPrice = null;
		try {
			String sql = "SELECT id,id_branches,id_service_type,default_price_code,price,created_by,created_date,version FROM default_price WHERE id = :id";
			Object result =  em.createNativeQuery(sql)
					.setParameter("id", id)
					.getSingleResult();
			if(result!=null) {
				defaultPrice = new DefaultPrice();
				Object[] objArr = (Object[]) result;
				defaultPrice.setId(Long.valueOf(objArr[0].toString()));
				Branch branch = new Branch();
				branch.setId(Long.valueOf(objArr[1].toString()));
				ServiceType serviceType = new ServiceType();
				serviceType.setId(Long.valueOf(objArr[2].toString()));
				defaultPrice.setBranch(branch);
				defaultPrice.setServiceType(serviceType);
				defaultPrice.setDefaultPriceCode(objArr[3].toString());
				defaultPrice.setPrice(new BigDecimal(objArr[4].toString()));
				defaultPrice.setCreatedBy(Long.valueOf(objArr[5].toString()));
				defaultPrice.setCreatedDate(((Timestamp)objArr[6]).toLocalDateTime());
				defaultPrice.setVersion((Integer)objArr[7]);
			}
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return defaultPrice;
	}

	@Override
	public DefaultPrice insert(DefaultPrice data) throws Exception {
		return insertData(data);
	}

	@Override
	public DefaultPrice update(DefaultPrice data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = "DELETE FROM default_price WHERE id =:id ";
		
		int result = em.createNativeQuery(sql)
				.setParameter("id", id)
				.executeUpdate();
		
		return result>0;
	}

}
