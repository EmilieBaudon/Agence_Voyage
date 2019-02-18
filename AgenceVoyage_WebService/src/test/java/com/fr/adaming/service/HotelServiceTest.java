package com.fr.adaming.service;

/**
 * This class contains all the unitary tests for the Hotel class. It will test all the Service Layer methods
 * as well as the Hibernate database constraints and conditions (Nullable, unique etc.). 
 * 
 * @author Quentin
 */

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
		testHotel = new Hotel("Hilton", "hotel de riche",null,null);
		service.create(testHotel);
		assertNotNull(testHotel);

	}

	// Créer hotel deja existant
	@Test
	public void b_createExistingHotelTest() {
		testHotel = new Hotel("Hilton", "hotel de riche",null,null);
		Hotel testHotel2 = new Hotel("Hilton", "hotel de riche",null,null);
		testHotel = service.create(testHotel);
		testHotel2 = service.create(testHotel2);
		assertNull(testHotel2);

	}

	// Créer un hotel avec ID null
	@Test
	public void c_createNullIdHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche",null,null);
		testHotel.setId(null);
		testHotel = service.create(testHotel);
		assertNotNull(testHotel);
	}

	// Créer un hotel avec ID = 0
	@Test
	public void d_createZeroIdHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche",null,null);
		testHotel.setId(0L);
		testHotel = service.create(testHotel);
		assertNotNull(testHotel);
	}

	// Créer un hotel avec nom déjà existant
	@Test(expected = DataIntegrityViolationException.class)
	public void e_createExistingNameHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche",null,null);
		service.create(testHotel);
		Hotel testHotel2 = new Hotel("Hilton", "hotel de Trump",null,null);
		testHotel2 = service.create(testHotel2);

	}

	// Créer un hotel avec nom null
	@Test(expected = DataIntegrityViolationException.class)
	public void f_createNullNameHotel() {
		testHotel = new Hotel(null, "hotel de riche",null,null);
		testHotel = service.create(testHotel);

	}

	// Modification d'un objet qui n'existe pas
	@Test
	public void g_updateNonExistingHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche",null,null);
		service.update(testHotel);
		testHotel.setId(999L);
		assertNull(testHotel);

	}

	// Modification d'un objet qui existe dans la BD
	@Test
	public void h_updateExistingHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche",null,null);
		testHotel = service.create(testHotel);
		testHotel = service.readAll().get(0);
		testHotel = service.update(testHotel);
		assertNotNull(testHotel);
	}

	// Update un objet avec un ID null
	@Test
	public void i_updateNullIdHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche",null,null);
		testHotel = service.create(testHotel);
		testHotel.setId(null);
		service.update(testHotel);
		assertNull(testHotel);
	}

	// Update un objet avec un ID =0
	@Test
	public void j_update0IdHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche",null,null);
		testHotel = service.create(testHotel);
		testHotel.setId(0L);
		service.update(testHotel);
		assertNull(testHotel);
	}

	// Delete Objet non-existant
	@Test
	public void k_deleteNonExistingHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche",null,null);
		Boolean boolTest = service.deleteById(testHotel.getId());
		assertFalse(boolTest);
	}
	
	//Delete objet existant
	@Test
	public void l_deleteExistingHotel() {
		testHotel = new Hotel("Hilton", "hotel de riche",null,null);
		testHotel = service.create(testHotel);
		Boolean boolTest = service.deleteById(testHotel.getId());
		assertTrue(boolTest);
	}

}
