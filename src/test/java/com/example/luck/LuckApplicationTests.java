package com.example.luck;

import com.example.luck.dao.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LuckApplicationTests {


	private SessionFactory sessionFactory;

	@Autowired
	private ClientDAO clientDAO;

	@Autowired
	private ContractDAO contractDAO;

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private ServiceDAO serviceDAO;

	@Autowired
	private WorkplaceDAO workplaceDAO;
/*
	@BeforeEach
	protected void setUp() throws Exception {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@AfterEach
	protected void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}
*/




	@Test
	public void testClientDAO() {
		assertEquals(5, clientDAO.getAll().size());

		Client ct = clientDAO.get(3L);
		assertEquals("vvp@mail.ru", ct.getEmail());
		ct.setEmail("medvedev@goyda.ru");
		clientDAO.update(ct);
		ct = clientDAO.get(3L);
		assertNotEquals("vvp@mail.ru", ct.getEmail());
		assertEquals("medvedev@goyda.ru", ct.getEmail());
		ct = clientDAO.get(3L);

		ct.setEmail("vvp@mail.ru");
		clientDAO.update(ct);
		assertEquals("vvp@mail.ru", ct.getEmail());

		ct.setId(6L);
		ct.setEmail("abasbodasdas@dasdsa.dassad");
		clientDAO.save(ct);
		assertEquals(6, clientDAO.getAll().size());
		clientDAO.delete(ct);
		assertEquals(5, clientDAO.getAll().size());
	}

	@Test
	void testContractDAO() {

		List<Contract> contractList = contractDAO.getAllSortedByDate();
		assertEquals(5, contractList.size());
		Contract contract = null;
		for (Contract ct : contractList) {
			assertTrue(contract == null || contract.getRealDate() == null
					|| ct.getRealDate() == null || contract.getRealDate().after(ct.getRealDate()));
			contract = ct;
		}
		contractList = contractDAO.getAllSortedById();
		assertEquals(5, contractList.size());
		contract = null;
		for (Contract ct : contractList) {
			assertTrue(contract == null || contract.getId() > ct.getId());
			contract = ct;
		}
		Employee employee = contractDAO.getEmployeeById(2L);
		assertEquals(employee.getId(), 4L);
		Client client = contractDAO.getClientById(5L);
		assertEquals(client.getId(), 2L);
		contractList = contractDAO.getContractByClientId(2L);
		assertEquals(2, contractList.size());
		for (Contract ct : contractList) {
			assertTrue(ct.getId() == 2L || ct.getId() == 5L);
		}
		contractList = contractDAO.getContractByDataRange(new Date(1212121212L), new Date(3434343434L));
		assertEquals(0, contractList.size());
		contractList = contractDAO.getContractByEmployeeId(1L);
		assertTrue(1 == contractList.size() && contractList.getFirst().getId() == 4L);
		contractList = contractDAO.getContractByEmployeeId(3L);
		assertEquals(0, contractList.size());
	}

	@Test
	void testEmployeeDAO() {
		List<Employee> empList = employeeDAO.getAllSortedById();
		Employee employee = null;
		for (Employee s : empList) {
			assertTrue(employee == null || s.getId() < employee.getId());
			employee = s;
		}
	}

	@Test
	void testServiceDAO() {
		List<Services> servicesList = serviceDAO.getAllSortedById();
		Services service = null;
		for (Services s : servicesList) {
			assertTrue(service == null || s.getId() > service.getId());
			service = s;
		}
	}

	@Test
	void testWorkplaceDAO() {
		List<Workplace> wpList = workplaceDAO.getAllSortedById();
		Workplace wp = null;
		for (Workplace s : wpList) {
			assertTrue(wp == null || s.getId() > wp.getId());
			wp = s;
		}
	}

	@Test
	void contextLoads() {
	}

}
