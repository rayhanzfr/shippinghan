package com.rayhan.shippinghan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rayhan.shippinghan.constant.StatusType;
import com.rayhan.shippinghan.dao.hibernate.hql.ShipmentDaoHibernateHQLImpl;
import com.rayhan.shippinghan.dao.hibernate.nativequery.ShipmentDaoHibernateNativeImpl;
import com.rayhan.shippinghan.model.Branch;
import com.rayhan.shippinghan.model.City;
import com.rayhan.shippinghan.model.PaymentMethod;
import com.rayhan.shippinghan.model.ServiceType;
import com.rayhan.shippinghan.model.Shipment;
import com.rayhan.shippinghan.model.Status;
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
public class ShipmentDaoTest {
	private static ShipmentDao shipmentDao;
	private static EntityManager emCentral;

	private static ShipmentDao shipmentDaoFactory(String dao, EntityManager em) {
		if (dao.equals("hql")) {
			ShipmentDaoHibernateHQLImpl shipmentDaoHQL = new ShipmentDaoHibernateHQLImpl();
			shipmentDaoHQL.setEntityManager(em);
			return shipmentDaoHQL;
		} else {
			ShipmentDaoHibernateNativeImpl shipmentDaoNative = new ShipmentDaoHibernateNativeImpl();
			shipmentDaoNative.setEntityManager(em);
			return shipmentDaoNative;
		}
	}

	@BeforeAll
	public static void init(@Autowired EntityManager em, @Value("${dao.test}") String dao) {
		emCentral = em;
		shipmentDao = shipmentDaoFactory(dao, em);

	}

	@Test
	@Order(5)
	public void shoudSuccessGetAll() throws Exception {
		int results = shipmentDao.getAll(1L, StatusType.NEW.getStatusCode()).size();
		assertEquals(results, 1);
	}

	@Test
	@Order(2)
	public void shoudSuccessGetById() throws Exception {
		Shipment result = shipmentDao.getById(1L);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(1)
	@Commit
	public void shouldSuccessInsert() throws Exception {
		Shipment shipment = new Shipment();
		ServiceType serviceType = new ServiceType();
		LocalDateTime localDateTime = LocalDateTime.now();
		serviceType.setServiceCode("9997");
		serviceType.setServiceName("Satanic");
		serviceType.setCreatedBy(1L);
		serviceType.setCreatedDate(localDateTime);
		serviceType.setIsActive(true);
		emCentral.persist(serviceType);
		City city = new City();
		city.setCityCode("9997");
		city.setNames("Satanic");
		city.setCreatedBy(1L);
		city.setCreatedDate(localDateTime);
		city.setIsActive(true);
		emCentral.persist(city);
		Branch branch = new Branch();
		branch.setCity(city);
		branch.setBranchesCode("9999");
		branch.setBranchesCode("HAHA");
		branch.setCreatedBy(1L);
		branch.setCreatedDate(localDateTime);
		branch.setIsActive(true);
		emCentral.persist(branch);
		Status status = new Status();
		status.setStatusCode("9997");
		status.setStatusName("Satanic");
		status.setCreatedBy(1L);
		status.setCreatedDate(localDateTime);
		status.setIsActive(true);
		emCentral.persist(status);
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setPaymentCode("9997");
		paymentMethod.setPaymentName("Satanic");
		paymentMethod.setCreatedBy(1L);
		paymentMethod.setCreatedDate(localDateTime);
		paymentMethod.setIsActive(true);
		emCentral.persist(paymentMethod);
		shipment.setServiceType(serviceType);
		shipment.setReceiverDestination(branch);
		shipment.setStatus(status);
		shipment.setPaymentMethod(paymentMethod);
		shipment.setShipmentNumber("9999");
		shipment.setShippingDate(localDateTime);
		shipment.setCreatedBy(1L);
		shipment.setCreatedDate(localDateTime);
		shipment.setIsActive(true);

		Shipment result = shipmentDao.insert(shipment);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(3)
	@Commit
	public void shouldSuccessUpdate() throws Exception {
		Shipment shipment = shipmentDao.getById(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		Status status = shipment.getStatus();
		status.setId(1L);
		shipment.setStatus(status);
		shipment.setUpdateBy(1L);
		shipment.setUpdateDate(localDateTime);
		shipment.setIsActive(true);
		shipment.setVersion(0);

		shipmentDao.update(shipment);
	}

	@Test
	@Order(4)
	public void checkSuccessUpdate() throws Exception {
		Shipment result = shipmentDao.getById(1L);
		assertEquals(result.getVersion(), 1);
	}

}
