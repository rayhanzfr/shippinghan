package com.rayhan.shippinghan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rayhan.shippinghan.dao.hibernate.hql.ProfileDaoHibernateHQLImpl;
import com.rayhan.shippinghan.dao.hibernate.nativequery.ProfileDaoHibernateNativeImpl;
import com.rayhan.shippinghan.model.Branch;
import com.rayhan.shippinghan.model.Profile;
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
public class ProfileDaoTest {
	private static ProfileDao profileDao;

	private static ProfileDao profileDaoFactory(String dao, EntityManager em) {
		if (dao.equals("hql")) {
			ProfileDaoHibernateHQLImpl profileDaoHQL = new ProfileDaoHibernateHQLImpl();
			profileDaoHQL.setEntityManager(em);
			return profileDaoHQL;
		} else {
			ProfileDaoHibernateNativeImpl profileDaoNative = new ProfileDaoHibernateNativeImpl();
			profileDaoNative.setEntityManager(em);
			return profileDaoNative;
		}
	}

	@BeforeAll
	public static void init(@Autowired EntityManager em, @Value("${dao.test}") String dao) {

		profileDao = profileDaoFactory(dao, em);

	}

	@Test
	@Order(3)
	public void shoudSuccessGetAll() throws Exception {
		int results = profileDao.getAll().size();
		assertEquals(results, 6);
	}

	@Test
	@Order(4)
	public void shoudSuccessGetById() throws Exception {
		Long id = 6L;
		Profile result = profileDao.getById(id);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(1)
	@Commit
	public void shouldSuccessInsert() throws Exception {
		Profile profile = new Profile();
		Branch branch = new Branch();
		branch.setId(2L);
		profile.setBranch(branch);
		profile.setProfileCode("9999");
		profile.setFirstNames("HAHAHA");
		profile.setCreatedBy(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		profile.setCreatedDate(localDateTime);
		profile.setIsActive(true);

		Profile result = profileDao.insert(profile);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(2)
	@Commit
	public void shouldSuccessUpdate() throws Exception {
		Profile profile = new Profile();
		Branch branch = new Branch();
		profile.setId(6L);
		branch.setId(3L);
		profile.setBranch(branch);
		profile.setProfileCode("9998");
		profile.setFirstNames("HAHAHI");
		profile.setCreatedBy(1L);
		profile.setUpdateBy(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		profile.setUpdateDate(localDateTime);
		profile.setIsActive(true);

		Profile result = profileDao.update(profile);
		assertEquals(result.getBranch().getId(), 3L);
		assertEquals(result.getProfileCode(), "9998");
		assertEquals(result.getFirstNames(), "HAHAHI");
	}

	@Test
	@Transactional
	@Order(5)
	@Commit
	public void shouldSuccessDelete() throws Exception {
		long id = 6L;

		boolean result = profileDao.deleteById(id);
		assertEquals(result, true);
	}

}
