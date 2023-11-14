package com.rayhan.shippinghan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rayhan.shippinghan.dao.hibernate.hql.RolesDaoHibernateHQLImpl;
import com.rayhan.shippinghan.dao.hibernate.nativequery.RolesDaoHibernateNativeImpl;
import com.rayhan.shippinghan.model.Roles;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class RolesDaoTest {
	
	private static RolesDao rolesDao;
	
	private static RolesDao roleDaoFactory(String dao, EntityManager em) {
		if(dao.equals("hql")) {
			RolesDaoHibernateHQLImpl rolesDaoHQL = new RolesDaoHibernateHQLImpl();
			rolesDaoHQL.setEntityManager(em);
			return rolesDaoHQL;
		}else {
			RolesDaoHibernateNativeImpl rolesDaoNative = new RolesDaoHibernateNativeImpl();
			rolesDaoNative.setEntityManager(em);
			return rolesDaoNative;
		}
	}
	@BeforeAll
	public static void init(@Autowired EntityManager em,
			@Value("${dao.test}")String dao) {
		
		rolesDao = roleDaoFactory(dao, em);

	}
	
	@Test
	@Order(5)
	public void shoudSuccessGetAll() throws Exception {
		int results = rolesDao.getAll().size();
		assertEquals(results, 1);
	}

	@Test
	@Order(2)
	public void shoudSuccessGetById() throws Exception {
		Long id = 1L;
		Roles result = rolesDao.getById(id);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(1)
	@Commit
	public void shouldSuccessInsert() throws Exception {
		Roles roles = new Roles();
		roles.setRoleCode("9997");
		roles.setNames("Satanic");
		roles.setCreatedBy(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		roles.setCreatedDate(localDateTime);
		roles.setIsActive(true);

		Roles result = rolesDao.insert(roles);
		assertNotNull(result.getId());
	}
	

	@Test
	@Transactional
	@Order(3)
	@Commit
	public void shouldSuccessUpdate() throws Exception {
		Roles roles = rolesDao.getById(1L);
		roles.setId(1L);
		roles.setRoleCode("9999");
		roles.setNames("HEHE");
		roles.setUpdateBy(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		roles.setUpdateDate(localDateTime);
		roles.setIsActive(true);
		roles.setVersion(0);

		rolesDao.update(roles);
	}
	
	@Test
	@Order(4)
	public void checkSuccessUpdate() throws Exception{
		Roles result = rolesDao.getById(1L);
		assertEquals(result.getVersion(),1);
	}

	@Test
	@Transactional
	@Order(6)
	@Commit
	public void shouldSuccessDelete() throws Exception {
		boolean result = rolesDao.deleteById(1L);
		assertEquals(result, true);
	}
}
