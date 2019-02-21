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

import com.fr.adaming.entity.Booking;
import com.fr.adaming.entity.Customer;
import com.fr.adaming.entity.Travel;
import com.fr.adaming.service.BookingService;
import com.fr.adaming.service.PersonService;
import com.fr.adaming.service.TravelService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BookingServiceTest {

	@Autowired
	private BookingService service;

	private Booking booking;

	@Autowired
	private TravelService serviceT;

	private Travel createdTravel;

	@Autowired
	private PersonService serviceC;

	private Customer createdCustomer;

	public void createNewTravel() {
		createdTravel = new Travel(5, "Maroc", LocalDate.of(2020, 05, 10), null, null, null, null);
		createdTravel = serviceT.create(createdTravel);
	}

	public void createNewCustomer() {
		createdCustomer = new Customer("Larry", "Sylvertstein", null, "rue de paris", "mail@mail.com",
				"exempleMotdePasse1");
		createdCustomer = (Customer) serviceC.create(createdCustomer);
	}

	// Création d'un objet valide
	@Test
	public void a_createBooking() {
		createNewTravel();
		createNewCustomer();

		booking = new Booking(5, 5, null, null, createdCustomer, createdTravel);
		booking = service.create(booking);
		assertNotNull(booking);

	}

	// Créer objet deja existant
	@Test
	public void b_createExistingBooking() {
		createNewTravel();
		createNewCustomer();
		booking = new Booking(5, 5, null, null, createdCustomer, createdTravel);
		Booking booking2 = booking;
		booking = service.create(booking);
		booking2 = service.create(booking2);
		assertNull(booking2);

	}

	// Créer un objet avec ID null
	@Test
	public void c_createNullIdBooking() {

		createNewTravel();
		createNewCustomer();
		booking = new Booking(5, 5, null, null, createdCustomer, createdTravel);
		booking.setId(null);
		booking = service.create(booking);
		assertNotNull(booking);
	}

	// Créer un objet avec ID = 0
	@Test
	public void d_createZeroIdBooking() {
		createNewTravel();
		createNewCustomer();
		booking = new Booking(5, 5, null, null, createdCustomer, createdTravel);
		booking.setId(0L);
		booking = service.create(booking);
		assertNotNull(booking);
	}

	// Modification d'un objet qui n'existe pas
	@Test
	public void g_updateNonExistingBooking() {
		booking = new Booking(5, 5, null, null, createdCustomer, createdTravel);
		booking.setId(9999999L);
		booking = service.update(booking);
		assertNull(booking);

	}

	// Modification d'un objet qui existe dans la BD
	@Test
	public void h_updateExistingBooking() {
		createNewTravel();
		createNewCustomer();
		booking = new Booking(5, 5, null, null, createdCustomer, createdTravel);
		booking = service.create(booking);
		booking = service.readAll().get(0);
		booking = service.update(booking);
		assertNotNull(booking);
	}

	// Update un objet avec un ID null
	@Test
	public void i_updateNullIdBooking() {
		createNewTravel();
		createNewCustomer();
		booking = new Booking(5, 5, null, null, createdCustomer, createdTravel);
		booking = service.create(booking);

		Booking booking2 = new Booking(8, 8, null, null, createdCustomer, createdTravel);
		booking2.setId(null);
		booking2 = service.update(booking2);
		assertNull(booking2);
	}

	// Update un objet avec un ID =0
	@Test
	public void j_update0IdBooking() {
		createNewTravel();
		createNewCustomer();
		booking = new Booking(5, 5, null, null, createdCustomer, createdTravel);
		booking = service.create(booking);

		Booking booking2 = new Booking(8, 8, null, null, createdCustomer, createdTravel);
		booking2.setId(0L);
		booking2 = service.update(booking2);
		assertNull(booking2);
	}

	// Delete Objet non-existant
	@Test
	public void k_deleteNonExistingBooking() {
		Boolean objDelete = service.deleteById(9999999L);
		assertFalse(objDelete);
	}

	// Delete objet existant
	@Test
	public void l_deleteExistingBooking() {
		createNewTravel();
		createNewCustomer();
		booking = new Booking(5, 5, null, null, createdCustomer, createdTravel);
		booking = service.create(booking);
		Boolean boolTest = service.deleteById(booking.getId());
		assertTrue(boolTest);
	}

	@Test
	public void m_readNonExistingBooking() {
		booking = service.readById(9999999L);
		assertNull(booking);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void n_readAllEmptyTable() {
		booking = service.readAll().get(0);
	}

	@Test
	public void readExistingBooking() {
		createNewTravel();
		createNewCustomer();
		booking = new Booking(5, 5, null, null, createdCustomer, createdTravel);
		booking = service.create(booking);
		booking = service.readAll().get(0);
		booking = service.readById(booking.getId());
		assertNotNull(booking);
	}

	@After // méthode sera appelé après chaque méthode de tests
	public void after() {
		System.out.println("************************DEBUG TESTING AfterMethod***********************");
		if (booking != null && booking.getId() != null) {
			service.deleteById(booking.getId());
			service.deleteById(createdTravel.getId());
			service.deleteById(createdCustomer.getId());

			if (createdTravel != null && createdTravel.getId() != null) {
				serviceT.deleteById(createdTravel.getId());
			}

			if (createdCustomer != null && createdCustomer.getId() != null) {
				serviceC.deleteById(createdCustomer.getId());
			}
		}
	}
}
