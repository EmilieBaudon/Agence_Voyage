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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.Service.TravelService;
import com.fr.adaming.entity.Travel;

/**
 * 
 * This test class checks the creation of a new travel
 *
 *
 * @author Nicolas
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TravelServiceTest {

	@Autowired
	private TravelService service;

	private Travel createdTravel;

	@Test
	public void a_createNewTravel() {
		// Create a new Travel
		createdTravel = new Travel(5, "Bora-Bora", LocalDate.of(2018, 06, 23), LocalDate.of(2018, 06, 28), null, null,
				null);
		createdTravel = service.create(createdTravel);
		assertNotNull(createdTravel);

	}

	@Test
	public void b_createExistingTravelId() {
		// Create a new existing Travel
		a_createNewTravel();
		Travel createdTravel2 = service.readAll().get(0);
		createdTravel2 = service.create(createdTravel2);
		assertNull(createdTravel2);
	}

	@Test
	public void c_createTravelWithNullId() {
		// Create a new Travel with null Id
		createdTravel = new Travel(5, "Bora-Bora", LocalDate.of(2018, 06, 23), LocalDate.of(2018, 06, 28), null, null,
				null);
		createdTravel.setId(null);
		createdTravel = service.create(createdTravel);
		assertNotNull(createdTravel);
	}

	@Test
	public void d_createHeroWithIdEqualsZero() {
		// Create a new Travel with Id equals to 0
		createdTravel = new Travel(5, "Bora-Bora", LocalDate.of(2018, 06, 23), LocalDate.of(2018, 06, 28), null, null,
				null);
		createdTravel.setId(0L);
		createdTravel = service.create(createdTravel);
		assertNotNull(createdTravel);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void e_createTravelWithNullDestination() {
		// Create a new Travel with null nb of nights
		createdTravel = new Travel(5, null, LocalDate.of(2018, 06, 23), LocalDate.of(2018, 06, 28), null, null, null);
		createdTravel = service.create(createdTravel);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void f_createTravelWithNullBeginningDate() {
		// Create a new Travel with null date period begin
		createdTravel = new Travel(5, "Bora-Bora", null, LocalDate.of(2018, 06, 28), null, null, null);
		createdTravel = service.create(createdTravel);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void g_createTravelWithNullEndingDate() {
		// Create a new Travel with null date period begin
		createdTravel = new Travel(5, "Bora-Bora", LocalDate.of(2018, 06, 28), null, null, null, null);
		createdTravel = service.create(createdTravel);
	}

	@Test
	public void e_updateNonExistingTravel() {
		// Update non existing travel
		createdTravel = new Travel(5, "Bora-Bora", LocalDate.of(2018, 06, 23), LocalDate.of(2018, 06, 28), null, null,
				null);
		createdTravel = service.create(createdTravel);
		createdTravel.setId(99L);
		createdTravel = service.update(createdTravel);
		assertNull(createdTravel);
	}

	@Test
	public void h_updateTravelWithNullId() {
		// Update travel with null id
		createdTravel = new Travel(5, "Bora-Bora", LocalDate.of(2018, 06, 23), LocalDate.of(2018, 06, 28), null, null,
				null);
		createdTravel = service.create(createdTravel);
		createdTravel.setId(null);
		createdTravel = service.update(createdTravel);
		assertNull(createdTravel);
	}

	@Test
	public void i_updateTravelWithIdEqualsZero() {
		// Update travel with id equals to zero
		createdTravel = new Travel(5, "Bora-Bora", LocalDate.of(2018, 06, 23), LocalDate.of(2018, 06, 28), null, null,
				null);
		createdTravel = service.create(createdTravel);
		createdTravel.setId(0L);
		createdTravel = service.update(createdTravel);
		assertNull(createdTravel);
	}

	@Test
	public void j_updateExistingTravel() {
		// Update existing travel
		a_createNewTravel();
		createdTravel.setDestination("testtttttttttttt");
		createdTravel = service.update(createdTravel);
		assertNotNull(createdTravel);
	}

	@Test
	public void k_deleteExistingTravelId() {
		// Delete existing Travel id
		Travel createdTravel = new Travel(5, "Bora-Bora", LocalDate.of(2018, 06, 23), LocalDate.of(2018, 06, 28), null,
				null, null);
		createdTravel = service.create(createdTravel);

		assertTrue(service.deleteById(createdTravel.getId()));
	}

	@Test
	public void l_deleteNonExistingTravelId() {
		// Delete non existing Travel id
		assertFalse(service.deleteById(99999L));

	}

	@After
	public void afterMethod() {
		// After method which delete a travel
		System.out.println("*****DEBUG TESTING afterMethod*****");
		if (createdTravel != null && createdTravel.getId() != null) {
			service.deleteById(createdTravel.getId());
		}
	}
}