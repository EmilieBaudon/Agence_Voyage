package com.fr.adaming.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.Service.FlightService;
import com.fr.adaming.Service.TravelService;
import com.fr.adaming.entity.Flight;
import com.fr.adaming.entity.Travel;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlightServiceTest {

	@Autowired
	private FlightService service;

	private Flight flight;

	@Autowired
	private TravelService tService;

	private Travel createdTravel;

	public void a_createNewTravel() {
		// Create a new Travel

		createdTravel = new Travel(1, "Bora-Bora", LocalDate.of(2020, 05, 10), null, null, null, null);
		createdTravel = tService.create(createdTravel);

	}

	@Test
	public void c_insertValid() {
		a_createNewTravel();
		flight = new Flight();
		flight.setTravel(createdTravel);
		assertNotNull(service.create(flight));
	}

	@Test
	public void d_insertExistingFlight() {
		c_insertValid();
		flight = service.readAll().get(0);
		assertNull(service.create(flight));
		System.out.println(createdTravel.getId());
	}

	@Test
	public void a_insertFlightWithNullId() {
		a_createNewTravel();
		flight = new Flight();
		flight.setId(null);
		flight.setTravel(createdTravel);
		flight = service.create(flight);
		assertNotNull(flight);
	}

	@Test
	public void b_insertFlightWithIdEqualsZero() {
		a_createNewTravel();
		flight = new Flight();
		flight.setId(0L);
		flight.setTravel(createdTravel);
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

	@Test
	public void readNonExistingObject() {
		flight = service.readById(9999999L);
		assertNull(flight);
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
			service.deleteById(createdTravel.getId());
		}
		if (createdTravel != null && createdTravel.getId() != null) {
			tService.deleteById(createdTravel.getId());
		}
	}
}
