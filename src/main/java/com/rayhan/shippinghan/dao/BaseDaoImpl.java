package com.rayhan.shippinghan.dao;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDaoImpl {
	protected Connection conn;
	protected EntityManager em;
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}
//	@Autowired
//	public void setDataSource(DataSource dataSource) throws SQLException {
//		this.conn=dataSource.getConnection();
//	}
	
	protected <T> T insertData(T data) {
		em.persist(data);
		return data;
	}
	
	protected <T> T updateData(T data) {
		data = em.merge(data);
		em.flush();
		em.detach(data);
		return data;
	}
	
}
