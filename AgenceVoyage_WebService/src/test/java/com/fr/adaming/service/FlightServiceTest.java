package com.fr.adaming.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.Service.FlightService;
import com.fr.adaming.entity.Flight;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlightServiceTest {

	@Autowired
	private FlightService service;

	private Flight flight;

	@Test
	public void c_insertValid() {
		flight = new Flight();
		assertNotNull(service.create(flight));
	}

	@Test
	public void d_insertExistingFlight() {
		c_insertValid();
		flight = service.readAll().get(0);
		assertNull(service.create(flight));
	}

	@Test
	public void a_insertFlightWithNullId() {
		flight = new Flight();
		flight.setId(null);
		System.out.println(flight);
		flight = service.create(flight);
		assertNotNull(flight);
	}

	@Test
	public void b_insertFlightWithIdEqualsZero() {
		flight = new Flight();
		flight.setId(0L);
		flight = service.create(flight);
		assertNotNull(flight);
	}

	@Test
	public void updateExistingFlight() {
		c_insertValid();
		flight = service.readAll().get(0);
		assertNotNull(service.update(flight));
	}

	@Test
	public void updateNotExistingFlight() {
		flight = new Flight();
		flight.setId(9999999L);
		flight = service.update(flight);
		assertNull(flight);
	}

	@Test
	public void updateFlightWithIdNull() {
		flight = new Flight();
		flight.setId(null);
		flight = service.update(flight);
		assertNull(flight);
	}

	@Test
	public void updateFlightWithIdEqualsZero() {
		flight = new Flight();
		flight.setId(0L);
		flight = service.update(flight);
		assertNull(flight);
	}

	@Test
	public void deleteNonExistingObject() {
		Boolean objDelete = service.deleteById(9999999L);
		assertFalse(objDelete);
	}

	@Test
	public void deleteExistingObject() {
		c_insertValid();
		flight = service.readAll().get(0);
		Boolean objDelete = service.deleteById(flight.getId());
		assertTrue(objDelete);
	}

	@Test(expected = NoSuchElementException.class)
	public void readNonExistingObject() {
		flight = service.readById(9999999L);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void readAllEmptyTable() {
		flight = service.readAll().get(0);
	}

	@Test
	public void readExistingObject() {
		c_insertValid();
		flight = service.readAll().get(0);
		flight = service.readById(flight.getId());
		assertNotNull(flight);
	}

	@After // méthode sera appelé après chaque méthode de tests
	public void after() {
		System.out.println("************************DEBUG TESTING AfterMethod***********************");
		if (flight != null && flight.getId() != null) {
			service.deleteById(flight.getId());
		}
	}
}
