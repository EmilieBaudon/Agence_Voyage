package com.fr.adaming.service;

/**
 * This class contains all the unitary tests for the Standing class. It will test all the Service Layer methods
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
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.Service.StandingService;
import com.fr.adaming.entity.Standing;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StandingServiceTest {

	@Autowired
	private StandingService service;

	private Standing testStanding;
	

	@After
	public void after() {
		System.out.println("********************DEBUG TESTING Method After******************");
	
		if (testStanding != null && testStanding.getId() != null) {
			service.deleteById(testStanding.getId());
					
		}
		

	}

	// Création d'un objet valide
	@Test
	public void a_createStandingTest() {
		testStanding = new Standing(1, 1, 2, "Desc", null, null);
		testStanding = service.create(testStanding);
		assertNotNull(testStanding);

	}

	// Créer objet deja existant
	@Test
	public void b_createExistingStandingTest() {
		testStanding = new Standing(1, 1, 2, "Desc", null, null);
		Standing testStanding2 = testStanding;
		testStanding = service.create(testStanding);
		testStanding2 = service.create(testStanding2);
		assertNull(testStanding2);

	}

	// Créer un objet avec ID null
	@Test
	public void c_createNullIdStanding() {
		testStanding = new Standing(1, 1, 2, "Desc", null, null);
		testStanding.setId(null);
		testStanding = service.create(testStanding);
		assertNotNull(testStanding);
	}

	// Créer un objet avec ID = 0
	@Test
	public void d_createZeroIdStanding() {
		testStanding = new Standing(1, 1, 2, "Desc", null, null);
		testStanding.setId(0L);
		testStanding = service.create(testStanding);
		assertNotNull(testStanding);
	}


	// Modification d'un objet qui n'existe pas
	@Test
	public void g_updateNonExistingStanding() {
		testStanding = new Standing(1, 1, 2, "Desc", null, null);
		testStanding.setId(999L);
		testStanding = service.update(testStanding);
		assertNull(testStanding);

	}

	// Modification d'un objet qui existe dans la BD
	@Test
	public void h_updateExistingStanding() {
		testStanding = new Standing(1, 1, 2, "Desc", null, null);
		testStanding = service.create(testStanding);
		testStanding = service.readAll().get(0);
		testStanding = service.update(testStanding);
		assertNotNull(testStanding);
	}

	// Update un objet avec un ID null
	@Test
	public void i_updateNullIdStanding() {
		testStanding = new Standing(1, 1, 2, "Desc", null, null);
		testStanding = service.create(testStanding);
		
		Standing testStanding2 = new Standing(1, 1, 2, "Desc", null, null);
		testStanding2.setId(null);
		System.out.println(testStanding.getId());
		testStanding2 = service.update(testStanding2);
		assertNull(testStanding2);
	}

	// Update un objet avec un ID =0
	@Test
	public void j_update0IdStanding() {
		testStanding = new Standing(1, 1, 2, "Desc", null, null);
		testStanding = service.create(testStanding);
		
		Standing testStanding2 = new Standing(1, 1, 2, "Desc", null, null);
		testStanding2.setId(0L);
		System.out.println(testStanding.getId());
		testStanding2 = service.update(testStanding2);
		assertNull(testStanding2);
	}

	// Delete Objet non-existant
	@Test
	public void k_deleteNonExistingStanding() {
		testStanding = new Standing(1, 1, 2, "Desc", null, null);
		Boolean boolTest = service.deleteById(testStanding.getId());
		assertFalse(boolTest);
	}
	
	//Delete objet existant
	@Test
	public void l_deleteExistingStanding() {
		testStanding = new Standing(1, 1, 2, "Desc", null, null);
		testStanding = service.create(testStanding);
		System.out.println("debug test delete"+testStanding.getId());
		Boolean boolTest = service.deleteById(testStanding.getId());
		assertTrue(boolTest);
	}

}
