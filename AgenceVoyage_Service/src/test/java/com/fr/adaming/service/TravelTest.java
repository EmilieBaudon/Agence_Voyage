package com.fr.adaming.service;

import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class TravelTest {

	@Autowired
	private TravelService service;

	private Travel createdTravel;

	
	@Test
	public void a_createNewTravel() {
		createdTravel = new Travel(5, "Bora-Bora", null, null, null, null, null);
		createdTravel = service.create(createdTravel);
		assertNotNull(createdTravel);
		
	}

}
