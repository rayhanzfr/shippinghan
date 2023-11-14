package com.rayhan.shippinghan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rayhan.shippinghan.dao.hibernate.hql.UserDaoHibernateHQLImpl;
import com.rayhan.shippinghan.dao.hibernate.nativequery.UserDaoHibernateNativeImpl;
import com.rayhan.shippinghan.model.Users;
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
public class UsersDaoTest {

	private static UserDao usersDao;

	private static UserDao usersDaoFactory(String dao, EntityManager em) {
		if (dao.equals("hql")) {
			UserDaoHibernateHQLImpl usersDaoHQL = new UserDaoHibernateHQLImpl();
			usersDaoHQL.setEntityManager(em);
			return usersDaoHQL;
		} else {
			UserDaoHibernateNativeImpl usersDaoNative = new UserDaoHibernateNativeImpl();
			usersDaoNative.setEntityManager(em);
			return usersDaoNative;
		}
	}

	@BeforeAll
	public static void init(@Autowired EntityManager em, @Value("${dao.test}") String dao) {

		usersDao = usersDaoFactory(dao, em);

	}

	@Test
	@Order(3)
	public void shoudSuccessGetAll() throws Exception {
		int results = usersDao.getAll().size();
		assertEquals(results, 6);
	}

	@Test
	@Order(4)
	public void shoudSuccessGetById() throws Exception {
		Long id = 6L;
		Users result = usersDao.getById(id);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(1)
	@Commit
	public void shouldSuccessInsert() throws Exception {
		Users users = new Users();
		users.setUsername("9999");
		users.setPass("Panasonic");
		users.setCreatedBy(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		users.setCreatedDate(localDateTime);
		users.setIsActive(true);

		Users result = usersDao.insert(users);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(2)
	@Commit
	public void shouldSuccessUpdate() throws Exception {
		Users users = new Users();
		users.setId(6L);
		users.setUsername("9999");
		users.setPass("HEHE");
		LocalDateTime localDateTime = LocalDateTime.now();
		users.setUpdateBy(1L);
		users.setUpdateDate(localDateTime);
		users.setIsActive(true);

		Users result = usersDao.update(users);
		assertEquals(result.getUsername(), "9999");
		assertEquals(result.getPass(), "HEHE");
	}

	@Test
	@Transactional
	@Order(5)
	@Commit
	public void shouldSuccessDelete() throws Exception {
		long id = 6L;

		boolean result = usersDao.deleteById(id);
		assertEquals(result, true);
	}
}