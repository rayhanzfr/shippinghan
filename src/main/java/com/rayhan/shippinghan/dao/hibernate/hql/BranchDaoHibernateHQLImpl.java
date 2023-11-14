package com.rayhan.shippinghan.dao.hibernate.hql;

import com.rayhan.shippinghan.model.Branch;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.BranchDao;

@Profile("hql")
@Repository
public class BranchDaoHibernateHQLImpl extends BaseDaoImpl implements BranchDao{

	@Override
	public List<Branch> getAll() throws Exception {
		List<Branch> list = em.createQuery("SELECT b From Branch as b INNER JOIN FETCH b.city",Branch.class)
				.getResultList();
		return list;
	}

	@Override
	public Branch getById(Long id) throws Exception {
		Branch branch = em.find(Branch.class, id);
		em.detach(branch);
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
		String sql="DELETE FROM Branch WHERE id = :id";
		
		int result = em.createQuery(sql)
			.setParameter("id", id)
			.executeUpdate();
		
		return result>0;
	}

}
