package com.fr.adaming.service;

import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.Service.HotelService;
import com.fr.adaming.entity.Hotel;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HotelServiceTest {

	private HotelService service;

	private Hotel testHotel;

	
	//Création d'un objet valide
	@Test
	public void a_createHotelTest() {
		testHotel = new Hotel("Hilton", "hotel de riche");
		service.create(testHotel);
		assertNotNull(testHotel);
		
	}
	
	//Créer hotel deja existant
	
	//Créer un hotel avec ID nulle
	
	//Créer un hotel avec ID = 0
	
	//
	

}
