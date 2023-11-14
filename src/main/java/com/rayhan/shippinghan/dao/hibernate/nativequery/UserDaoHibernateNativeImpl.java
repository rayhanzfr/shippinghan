package com.rayhan.shippinghan.dao.hibernate.nativequery;

import com.rayhan.shippinghan.model.Roles;
import com.rayhan.shippinghan.model.Users;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.UserDao;

@Profile("native")
@Repository(value = "userDaoNative")
public class UserDaoHibernateNativeImpl extends BaseDaoImpl implements UserDao{
	@Override
	public List<Users> getAll() throws Exception {
		String sql = "SELECT id,id_role,usernames,pass,created_by,created_date,isactive,update_by,update_date,version FROM users";
		List<?> result = em.createNativeQuery(sql.toString()).getResultList();
		List<Users> listUsers = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			Users users = new Users();
			Roles roles = new Roles();
			users.setId(Long.valueOf(objArr[0].toString()));
			roles.setId(Long.valueOf(objArr[1].toString()));
			users.setUsername(objArr[2].toString());
			users.setPass(objArr[3].toString());
			users.setCreatedBy(Long.valueOf(objArr[4].toString()));
			users.setCreatedDate(((Timestamp)objArr[5]).toLocalDateTime());
			users.setVersion((Integer)objArr[9]);
			users.setRoles(roles);
			listUsers.add(users);
			
		});
		return listUsers;
	}

	@Override
	public Users getById(Long id) throws Exception {
		Users users = null;
		try {
			String sql = "SELECT id,id_role,usernames,pass,created_by,created_date,isactive,update_by,update_date,version FROM users WHERE id =:id ";
			Object result =  em.createNativeQuery(sql)
					.setParameter("id", id)
					.getSingleResult();
			if(result!=null) {
				users = new Users();
				Object[] objArr = (Object[]) result;
				Roles roles = new Roles();
				users.setId(Long.valueOf(objArr[0].toString()));
				roles.setId(Long.valueOf(objArr[1].toString()));
				users.setUsername(objArr[2].toString());
				users.setPass(objArr[3].toString());
				users.setCreatedBy(Long.valueOf(objArr[4].toString()));
				users.setCreatedDate(((Timestamp)objArr[5]).toLocalDateTime());
				users.setVersion((Integer)objArr[9]);
				users.setRoles(roles);
			}
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public Users insert(Users data) throws Exception {
		return insertData(data);
	}

	@Override
	public Users update(Users data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = "DELETE FROM users_type WHERE id = :id ";
		
		int result = em.createNativeQuery(sql)
				.setParameter("id", id)
				.executeUpdate();
		
		return result>0;
	}

	@Override
	public Users getByUsername(String usernames) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
