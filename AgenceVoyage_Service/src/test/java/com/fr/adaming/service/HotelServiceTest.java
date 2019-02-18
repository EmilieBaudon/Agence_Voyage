package com.fr.adaming.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.Service.HotelService;
import com.fr.adaming.entity.Hotel;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HotelServiceTest {

	@Autowired
	private HotelService service;

	private Hotel testHotel;

	@After
	public void after() {
		System.out.println("********************DEBUG TESTING Method After******************");
		if (testHotel != null && testHotel.getId() != null) {
			service.deleteById(testHotel.getId());
		}

	}

	// Création d'un objet valide
	@Test
	public void a_createHotelTest() {
		testHotel = new Hotel("Hilton", "hotel de riche");
		service.create(testHotel);
		assertNotNull(testHotel);

	}

	// Créer hotel deja existant
	@Test
	public void b_createExistingHotelTest() {
		testHotel = new Hotel("Hilton", "hotel de riche");
		Hotel testHotel2 = new Hotel("Hilton", "hotel de riche");
		testHotel = service.create(testHotel);
		testHotel2 = service.create(testHotel2);
		assertNull(testHotel2);

	}

	// Créer un hotel avec ID nulle
	@Test
	public void c_createNullIdHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche");
		testHotel.setId(null);
		testHotel = service.create(testHotel);
		assertNotNull(testHotel);
	}

	// Créer un hotel avec ID = 0
	@Test
	public void d_createZeroIdHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche");
		testHotel.setId(0L);
		testHotel = service.create(testHotel);
		assertNotNull(testHotel);
	}

	// Créer un hotel avec nom déjà existant
	@Test(expected = DataIntegrityViolationException.class)
	public void e_createExistingNameHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche");
		service.create(testHotel);
		Hotel testHotel2 = new Hotel("Hilton", "hotel de Trump");
		testHotel2 = service.create(testHotel2);

	}

	// Créer un hotel avec nom null
	@Test
	public void f_createNullNameHotel() {
		testHotel = new Hotel(null, "hotel de riche");
		service.create(testHotel);

	}

	// Modification d'un objet qui n'existe pas
	@Test
	public void f_updateNonExistingHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche");
		service.update(testHotel);
		testHotel.setId(999L);
		assertNull(testHotel);

	}

	// Modification d'un objet qui existe dans la BD
	@Test
	public void g_updateExistingHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche");
		testHotel = service.create(testHotel);
		testHotel = service.readAll().get(0);
		testHotel = service.update(testHotel);
		assertNotNull(testHotel);
	}

	// Update un objet avec un ID null
	@Test
	public void h_updateNullIdHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche");
		testHotel = service.create(testHotel);
		testHotel.setId(null);
		service.update(testHotel);
		assertNull(testHotel);
	}

	// Update un objet avec un ID =0
	@Test
	public void i_update0IdHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche");
		testHotel = service.create(testHotel);
		testHotel.setId(0L);
		service.update(testHotel);
		assertNull(testHotel);
	}

	// Delete Objet non-existant
	@Test(expected = EmptyResultDataAccessException.class)
	public void j_deleteNonExistingHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche");
		testHotel.setId(999999999L);
	}

}
