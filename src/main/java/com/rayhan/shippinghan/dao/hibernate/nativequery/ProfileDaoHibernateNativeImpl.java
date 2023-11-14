package com.rayhan.shippinghan.dao.hibernate.nativequery;

import com.rayhan.shippinghan.model.Branch;
import com.rayhan.shippinghan.model.Profile;
import com.rayhan.shippinghan.model.Roles;
import com.rayhan.shippinghan.model.Users;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.ProfileDao;

@org.springframework.context.annotation.Profile("native")
@Repository(value = "profileDaoNative")
public class ProfileDaoHibernateNativeImpl extends BaseDaoImpl implements ProfileDao{

	@Override
	public List<Profile> getAll() throws Exception {
		String sql = "SELECT id,id_branch,profile_code,firstnames,lastnames,created_by,created_date,update_by,update_by,isactive,version FROM profile";
		List<?> result = em.createNativeQuery(sql.toString()).getResultList();
		List<Profile> listProfile = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			Profile profile = new Profile();
			Branch branch = new Branch();
			profile.setId(Long.valueOf(objArr[0].toString()));
			branch.setId(Long.valueOf(objArr[1].toString()));
			profile.setBranch(branch);
			profile.setProfileCode(objArr[2].toString());
			profile.setFirstNames(objArr[3].toString());
			profile.setLastNames(objArr[4].toString());
			profile.setCreatedBy(Long.valueOf(objArr[5].toString()));
			profile.setIsActive((Boolean) objArr[7]);
			profile.setVersion((Integer)objArr[9]);
			listProfile.add(profile);
			
		});
		return listProfile;
	}

	@Override
	public Profile getById(Long id) throws Exception {
		Profile profile = null;
		try {
			String sql = "id,id_branch,profile_code,firstnames,lastnames,created_by,created_date,update_by,update_by,isactive,version FROM profile WHERE id =? ";
			Object result =  em.createNativeQuery(sql)
					.setParameter("id", id)
					.getSingleResult();
			if(result!=null) {
				profile = new Profile();
				Object[] objArr = (Object[]) result;
				profile.setId(Long.valueOf(objArr[0].toString()));
				Branch branch = new Branch();
				branch.setId(Long.valueOf(objArr[1].toString()));
				profile.setBranch(branch);
				profile.setProfileCode(objArr[2].toString());
				profile.setFirstNames(objArr[3].toString());
				profile.setLastNames(objArr[4].toString());
				profile.setCreatedBy(Long.valueOf(objArr[5].toString()));
				profile.setIsActive((Boolean) objArr[7]);
				profile.setVersion((Integer)objArr[9]);
			}
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return profile;
	}

	@Override
	public Profile insert(Profile data) throws Exception {
		return insertData(data);
	}

	@Override
	public Profile update(Profile data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = "DELETE FROM default_price WHERE id = :id ";
		
		int result = em.createNativeQuery(sql)
				.setParameter("id", id)
				.executeUpdate();
		
		return result>0;
	}

	@Override
	public Profile getByUsernameAndPass(String username, String pass) throws Exception {
		Profile profile =null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT p.id,p.id_user,u.usernames,p.firstnames,p.lastnames, r.role_code, p.id_branch, b.branches_name ");
			sql.append(" FROM profile p ");
			sql.append(" INNER JOIN users u ON p.id_user = u.id " + "INNER JOIN roles r ON u.id_role = r.id ");
			sql.append(" INNER JOIN branches b ON p.id_branch = b.id ");
			sql.append(" WHERE u.usernames = :usernames AND u.pass= :pass ");
			Object result =  em.createNativeQuery(sql.toString())
					.setParameter("usernames", username)
					.setParameter("pass", pass)
					.getSingleResult();
			if(result!=null) {
				profile = new Profile();
				Object[] objArr = (Object[]) result;
				profile.setId(Long.valueOf(objArr[0].toString()));
				Branch branch = new Branch();
				Users users = new Users();
				Roles roles = new Roles();
				users.setId(Long.valueOf(objArr[1].toString()));
				users.setUsername(objArr[2].toString());
				profile.setFirstNames(objArr[3].toString());
				profile.setLastNames(objArr[4].toString());
				roles.setRoleCode(objArr[5].toString());
				branch.setId(Long.valueOf(objArr[6].toString()));
				branch.setBranchesName(objArr[7].toString());
				profile.setBranch(branch);
				users.setRoles(roles);
				profile.setUsers(users);
				
			}
		}catch(NoResultException e){
			e.printStackTrace();
		}
	return profile;
	}

	@Override
	public Profile getByUsersId(Long usersId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
