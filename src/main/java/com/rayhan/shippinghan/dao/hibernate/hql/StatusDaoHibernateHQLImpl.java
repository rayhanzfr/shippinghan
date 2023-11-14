package com.rayhan.shippinghan.dao.hibernate.hql;

import com.rayhan.shippinghan.model.Status;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.StatusDao;

@Profile("hql")
@Repository
public class StatusDaoHibernateHQLImpl extends BaseDaoImpl implements StatusDao{
	@Override
	public List<Status> getAll() throws Exception {
		String sql = "SELECT s FROM Status as s";
		List<Status> result = em.createQuery(sql,Status.class).getResultList();
		return result;
	}

	@Override
	public Status getById(Long id) throws Exception {
		Status status = null;
		try {
			status = em.find(Status.class, id);
			em.detach(status);
			return status;
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
		String sql="DELETE FROM Status WHERE id = :id";
		
		int result = em.createQuery(sql)
			.setParameter("id", id)
			.executeUpdate();
		
		return result>0;
	}

	@Override
	public Status getByStatusCode(String statusCode) throws Exception {
		Status status = null;
		try {
			String sql = "SELECT s FROM Status as s WHERE statusCode = :statusCode";
			status =  em.createQuery(sql,Status.class)
					.setParameter("statusCode", statusCode)
					.getSingleResult();
			return status;
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return status;
	}
}
