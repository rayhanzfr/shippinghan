package com.rayhan.shippinghan.dao.hibernate.nativequery;

import com.rayhan.shippinghan.model.Branch;
import com.rayhan.shippinghan.model.City;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.BranchDao;

@Profile("native")
@Repository
public class BranchDaoHibernateNativeImpl extends BaseDaoImpl implements BranchDao {

	@Override
	public List<Branch> getAll() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT id,id_city,branches_name,branches_code,created_by,created_date,isactive,update_by,update_date,version ");
		sql.append("FROM branches ");
		List<?> result = em.createNativeQuery(sql.toString()).getResultList();
		List<Branch> listBranch = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			Branch branch = new Branch();
			City city = new City();
			branch.setId(Long.valueOf(objArr[0].toString()));
			city.setId(Long.valueOf(objArr[1].toString()));
			branch.setCity(city);
			branch.setBranchesName(objArr[2].toString());
			branch.setBranchesCode(objArr[3].toString());
			branch.setCreatedBy(Long.valueOf(objArr[4].toString()));
			branch.setCreatedDate(((Timestamp) objArr[5]).toLocalDateTime());
			listBranch.add(branch);

		});
		return listBranch;
	}

	@Override
	public Branch getById(Long id) throws Exception {
		Branch branch = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					" SELECT b.id,b.id_city,b.branches_name,b.branches_code,b.created_by,b.created_date,b.isactive,b.update_by,b.update_date,b.version");
			sql.append(" FROM branches b ");
			sql.append(" INNER JOIN cities c ON c.id = b.id_city ");
			sql.append(" WHERE b.id = :id ");
			Object result = em.createNativeQuery(sql.toString()).setParameter("id", id).getSingleResult();
			if (result != null) {
				branch = new Branch();
				Object[] objArr = (Object[]) result;
				branch.setId(Long.valueOf(objArr[0].toString()));
				City city = new City();
				city.setId(Long.valueOf(objArr[1].toString()));
				branch.setCity(city);
				branch.setBranchesName(objArr[2].toString());
				branch.setBranchesCode(objArr[3].toString());
				branch.setCreatedBy(Long.valueOf(objArr[4].toString()));
				branch.setCreatedDate(((Timestamp) objArr[5]).toLocalDateTime());
				branch.setIsActive((Boolean) objArr[6]);
				branch.setVersion((Integer) objArr[9]);
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			throw new NoResultException("Not Found");
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException("Found more than one");
		}
		return branch;
	}

	@Override
	public Branch insert(Branch data) throws Exception {
		return insertData(data);
	}

	@Override
	public Branch update(Branch data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = "DELETE FROM branches WHERE id = :id";

		int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

}
