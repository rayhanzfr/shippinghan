package com.rayhan.shippinghan.dao.hibernate.nativequery;

import com.rayhan.shippinghan.model.ServiceType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.ServiceTypeDao;

@Profile("native")
@Repository(value = "serviceTypeDaoNative")
public class ServiceTypeDaoHibernateNativeImpl extends BaseDaoImpl implements ServiceTypeDao{
	@Override
	public List<ServiceType> getAll() throws Exception {
		String sql = "SELECT id,service_code,service_name,created_by,created_date,isactive,update_by,update_date,version FROM service_type";
		List<?> result = em.createNativeQuery(sql.toString()).getResultList();
		List<ServiceType> listServiceType = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			ServiceType serviceType = new ServiceType();
			serviceType.setId(Long.valueOf(objArr[0].toString()));
			serviceType.setServiceCode(objArr[1].toString());
			serviceType.setServiceName(objArr[2].toString());
			serviceType.setCreatedBy(Long.valueOf(objArr[3].toString()));
			serviceType.setIsActive((Boolean)objArr[5]);
			serviceType.setVersion((Integer)objArr[8]);
			listServiceType.add(serviceType);
			
		});
		return listServiceType;
	}

	@Override
	public ServiceType getById(Long id) throws Exception {
		ServiceType serviceType = null;
		try {
			String sql = "SELECT id,service_code,service_name,created_by,created_date,isactive,update_by,update_date,version FROM service_type WHERE id = :id ";
			Object result =  em.createNativeQuery(sql)
					.setParameter("id", id)
					.getSingleResult();
			if(result!=null) {
				serviceType = new ServiceType();
				Object[] objArr = (Object[]) result;
				serviceType.setId(Long.valueOf(objArr[0].toString()));
				serviceType.setServiceCode(objArr[1].toString());
				serviceType.setServiceName(objArr[2].toString());
				serviceType.setCreatedBy(Long.valueOf(objArr[3].toString()));
				serviceType.setCreatedDate(((Timestamp)objArr[4]).toLocalDateTime());
				serviceType.setVersion((Integer)objArr[8]);
			}
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
		String sql = "DELETE FROM service_type WHERE id = :id ";
		
		int result = em.createNativeQuery(sql)
				.setParameter("id", id)
				.executeUpdate();
		
		return result>0;
	}
}
