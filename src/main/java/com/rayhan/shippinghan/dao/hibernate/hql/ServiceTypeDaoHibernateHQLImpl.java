package com.rayhan.shippinghan.dao.hibernate.hql;

import com.rayhan.shippinghan.model.ServiceType;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.ServiceTypeDao;

@Profile("hql")
@Repository
public class ServiceTypeDaoHibernateHQLImpl extends BaseDaoImpl implements ServiceTypeDao{
	@Override
	public List<ServiceType> getAll() throws Exception {
		String sql = "SELECT st FROM ServiceType as st";
		List<ServiceType> result = em.createQuery(sql,ServiceType.class).getResultList();
		return result;
	}

	@Override
	public ServiceType getById(Long id) throws Exception {
		ServiceType serviceType = null;
		try {
			serviceType =  em.find(ServiceType.class, id);
			em.detach(serviceType);
			return serviceType;
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return serviceType;
	}

	@Override
	public ServiceType insert(ServiceType data) throws Exception {
		return insertData(data);
	}

	@Override
	public ServiceType update(ServiceType data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql="DELETE FROM ServiceType WHERE id = :id";
		
		int result = em.createQuery(sql)
			.setParameter("id", id)
			.executeUpdate();
		
		return result>0;
	}
}
