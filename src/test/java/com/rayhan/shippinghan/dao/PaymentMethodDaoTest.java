package com.rayhan.shippinghan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rayhan.shippinghan.dao.hibernate.hql.PaymentMethodDaoHibernateHQLImpl;
import com.rayhan.shippinghan.dao.hibernate.nativequery.PaymentMethodDaoHibernateNativeImpl;
import com.rayhan.shippinghan.model.PaymentMethod;
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
public class PaymentMethodDaoTest {
	private static PaymentMethodDao paymentMethodDao;

	private static PaymentMethodDao paymentMethodDaoFactory(String dao, EntityManager em) {
		if (dao.equals("hql")) {
			PaymentMethodDaoHibernateHQLImpl paymentMethodDaoHQL = new PaymentMethodDaoHibernateHQLImpl();
			paymentMethodDaoHQL.setEntityManager(em);
			return paymentMethodDaoHQL;
		} else {
			PaymentMethodDaoHibernateNativeImpl paymentMethodDaoNative = new PaymentMethodDaoHibernateNativeImpl();
			paymentMethodDaoNative.setEntityManager(em);
			return paymentMethodDaoNative;
		}
	}

	@BeforeAll
	public static void init(@Autowired EntityManager em, @Value("${dao.test}") String dao) {

		paymentMethodDao = paymentMethodDaoFactory(dao, em);

	}

	@Test
	@Order(3)
	public void shoudSuccessGetAll() throws Exception {
		int results = paymentMethodDao.getAll().size();
		assertEquals(results, 6);
	}

	@Test
	@Order(4)
	public void shoudSuccessGetById() throws Exception {
		Long id = 6L;
		PaymentMethod result = paymentMethodDao.getById(id);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(1)
	@Commit
	public void shouldSuccessInsert() throws Exception {
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setPaymentCode("9997");
		paymentMethod.setPaymentName("Satanic");
		paymentMethod.setCreatedBy(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		paymentMethod.setCreatedDate(localDateTime);
		paymentMethod.setIsActive(true);

		PaymentMethod result = paymentMethodDao.insert(paymentMethod);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(2)
	@Commit
	public void shouldSuccessUpdate() throws Exception {
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setId(6L);
		paymentMethod.setPaymentCode("9999");
		paymentMethod.setPaymentName("HEHE");
		LocalDateTime localDateTime = LocalDateTime.now();
		paymentMethod.setUpdateBy(1L);
		paymentMethod.setUpdateDate(localDateTime);
		paymentMethod.setIsActive(true);

		PaymentMethod result = paymentMethodDao.update(paymentMethod);
		assertEquals(result.getPaymentCode(), "9999");
		assertEquals(result.getPaymentName(), "HEHE");
	}

	@Test
	@Transactional
	@Order(5)
	@Commit
	public void shouldSuccessDelete() throws Exception {
		long id = 6L;

		boolean result = paymentMethodDao.deleteById(id);
		assertEquals(result, true);
	}
}
