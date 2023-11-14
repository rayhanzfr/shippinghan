package com.rayhan.shippinghan.dao.hibernate.nativequery;

import com.rayhan.shippinghan.model.Status;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.StatusDao;

@Profile("native")
@Repository(value = "statusDaoNative")
public class StatusDaoHibernateNativeImpl extends BaseDaoImpl implements StatusDao {
	@Override
	public List<Status> getAll() throws Exception {
		String sql = "SELECT id,status_name,status_code,created_by,created_date,isactive,update_by,update_date,version FROM status";
		List<?> result = em.createNativeQuery(sql.toString()).getResultList();
		List<Status> listStatus = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			Status status = new Status();
			status.setId(Long.valueOf(objArr[0].toString()));
			status.setStatusCode(objArr[1].toString());
			status.setStatusName(objArr[2].toString());
			status.setCreatedBy(Long.valueOf(objArr[3].toString()));
			status.setCreatedDate(((Timestamp)objArr[4]).toLocalDateTime());
			status.setIsActive((Boolean)objArr[6]);
			status.setVersion(Long.valueOf(objArr[8].toString()).intValue());
			listStatus.add(status);
			
		});
		return listStatus;
	}

	@Override
	public Status getById(Long id) throws Exception {
		Status status = null;
		try {
			String sql = "SELECT id,status_name,status_code,created_by,created_date,isactive,update_by,update_date,version FROM status WHERE id =:id ";
			Object result =  em.createNativeQuery(sql)
					.setParameter("id", id)
					.getSingleResult();
			if(result!=null) {
				status = new Status();
				Object[] objArr = (Object[]) result;
				status.setId(Long.valueOf(objArr[0].toString()));
				status.setStatusName(objArr[1].toString());
				status.setStatusCode(objArr[2].toString());
				status.setCreatedBy(Long.valueOf(objArr[3].toString()));
				status.setCreatedDate(((Timestamp)objArr[4]).toLocalDateTime());
				status.setIsActive((Boolean)objArr[6]);
				status.setVersion(Long.valueOf(objArr[8].toString()).intValue());
				return status;
			}
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Status insert(Status data) throws Exception {
		return insertData(data);
	}

	@Override
	public Status update(Status data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = "DELETE FROM status WHERE id = :id ";
		
		int result = em.createNativeQuery(sql)
				.setParameter("id", id)
				.executeUpdate();
		
		return result>0;
	}

	@Override
	public Status getByStatusCode(String statusCode) throws Exception {
		Status status = null;
		try {
			String sql = "SELECT id,status_name,status_code,created_by,created_date,isactive,update_by,update_date,version FROM status WHERE status_code =:status_code ";
			Object result =  em.createNativeQuery(sql)
					.setParameter("status_code", statusCode)
					.getSingleResult();
			if(result!=null) {
				status = new Status();
				Object[] objArr = (Object[]) result;
				status.setId(Long.valueOf(objArr[0].toString()));
				status.setStatusName(objArr[1].toString());
				status.setStatusCode(objArr[2].toString());
				status.setCreatedBy(Long.parseLong(objArr[3].toString()));
				status.setCreatedDate(((Timestamp)objArr[4]).toLocalDateTime());
			}
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return status;
	}
}
