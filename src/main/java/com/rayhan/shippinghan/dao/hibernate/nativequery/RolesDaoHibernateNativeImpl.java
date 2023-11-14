package com.rayhan.shippinghan.dao.hibernate.nativequery;

import com.rayhan.shippinghan.model.Roles;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.RolesDao;

@Profile("native")
@Repository(value = "rolesDaoNative")
public class RolesDaoHibernateNativeImpl extends BaseDaoImpl implements RolesDao {
	
	@Override
	public List<Roles> getAll() throws Exception {
		String sql = "SELECT id,names,role_code,created_by,created_date,isactive,update_by,update_date,version FROM roles";
		List<?> result = em.createNativeQuery(sql).getResultList();
		List<Roles> listRoles = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			Roles roles = new Roles();
			roles.setId(Long.valueOf(objArr[0].toString()));
			roles.setNames(objArr[1].toString());
			roles.setRoleCode(objArr[2].toString());
			roles.setCreatedBy(Long.valueOf(objArr[3].toString()));
			roles.setCreatedDate(((Timestamp)objArr[4]).toLocalDateTime());
			if(objArr[8]!=null) {					
				roles.setVersion((Integer)objArr[8]);
			}
			listRoles.add(roles);
			
		});
		return listRoles;
	}

	@Override
	public Roles getById(Long id) throws Exception {
//		return em.find(Roles.class, id);
		Roles roles = null;
		try {
			String sql = "SELECT id,names,role_code,created_by,created_date,isactive,update_by,update_date,version FROM roles WHERE id = :id";
			Object result =  em.createNativeQuery(sql)
					.setParameter("id", id)
					.getSingleResult();
			if(result!=null) {
				roles = new Roles();
				Object[] objArr = (Object[]) result;
				roles.setId(Long.valueOf(objArr[0].toString()));
				roles.setNames(objArr[1].toString());
				roles.setRoleCode(objArr[2].toString());
				roles.setCreatedBy(Long.valueOf(objArr[3].toString()));
				roles.setCreatedDate(((Timestamp)objArr[4]).toLocalDateTime());
				roles.setVersion(Long.valueOf(objArr[8].toString()).intValue());
			}
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return roles;
	}

	@Override
	public Roles insert(Roles data) throws Exception {
		return insertData(data);
	}

	@Override
	public Roles update(Roles data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql="DELETE FROM roles WHERE id = :id";
		
		int result = em.createNativeQuery(sql)
			.setParameter("id", id)
			.executeUpdate();
		
		return result>0;
	}

}
