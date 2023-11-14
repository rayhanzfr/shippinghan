package com.rayhan.shippinghan.dao.hibernate.hql;

import com.rayhan.shippinghan.model.City;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.CityDao;

@Profile("hql")
@Repository
public class CityDaoHibernateHQLImpl extends BaseDaoImpl implements CityDao{
	@Override
	public List<City> getAll() throws Exception {
		String sql = "SELECT c FROM City as c";
		List<City> result = em.createQuery(sql,City.class).getResultList();
		return result;
	}

	@Override
	public City getById(Long id) throws Exception {
		City city = null;
		try {
			city =  em.find(City.class, id);
			em.detach(city);
			return city;
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return city;
	}

	@Override
	public City insert(City data) throws Exception {
		return insertData(data);
	}

	@Override
	public City update(City data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql="DELETE FROM City WHERE id = :id";
		
		int result = em.createQuery(sql)
			.setParameter("id", id)
			.executeUpdate();
		
		return result>0;
	}
}
