package com.rayhan.shippinghan.dao.hibernate.hql;

import com.rayhan.shippinghan.model.DefaultPrice;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.DefaultPriceDao;

@Profile("hql")
@Repository
public class DefaultPriceDaoHibernateHQLImpl extends BaseDaoImpl implements DefaultPriceDao{
	
	@Override
	public List<DefaultPrice> getAll() throws Exception {
		String sql = "SELECT d FROM DefaultPrice as d INNER JOIN FETCH d.branch INNER JOIN FETCH d.serviceType ";
		List<DefaultPrice> result = em.createQuery(sql,DefaultPrice.class).getResultList();
		return result;
	}

	@Override
	public DefaultPrice getById(Long id) throws Exception {
		DefaultPrice defaultPrice = null;
		try {
			defaultPrice = em.find(DefaultPrice.class, id);
			em.detach(defaultPrice);
			return defaultPrice;
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
		String sql="DELETE FROM DefaultPrice WHERE id = :id";
		
		int result = em.createQuery(sql)
			.setParameter("id", id)
			.executeUpdate();
		
		return result>0;
	}
	
}
