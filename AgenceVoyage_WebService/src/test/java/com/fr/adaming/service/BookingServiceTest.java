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

import com.fr.adaming.Service.BookingService;
import com.fr.adaming.entity.Booking;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BookingServiceTest {

	@Autowired
	private BookingService service;

	private Booking booking;

	@Test
	public void c_insertValid() {
		booking = new Booking();
		assertNotNull(service.create(booking));
	}

	@Test
	public void d_insertExistingBooking() {
		c_insertValid();
		booking = service.readAll().get(0);
		assertNull(service.create(booking));
	}

	@Test
	public void a_insertBookingWithNullId() {
		booking = new Booking();
		booking.setId(null);
		assertNotNull(service.create(booking));
	}

	@Test
	public void b_insertBookingWithIdEqualsZero() {
		booking = new Booking();
		booking.setId(0L);
		booking = service.create(booking);
		assertNotNull(booking);
	}

	@Test
	public void updateExistingBooking() {
		c_insertValid();
		booking = service.readAll().get(0);
		assertNotNull(service.update(booking));
	}

	@Test
	public void updateNotExistingBooking() {
		booking = new Booking();
		booking.setId(9999999L);
		booking = service.update(booking);
		assertNull(booking);
	}

	@Test
	public void updateBookingWithIdNull() {
		booking = new Booking();
		booking.setId(null);
		booking = service.update(booking);
		assertNull(booking);
	}

	@Test
	public void updateBookingWithIdEqualsZero() {
		booking = new Booking();
		booking.setId(0L);
		booking = service.update(booking);
		assertNull(booking);
	}

	@Test
	public void deleteNonExistingObject() {
		Boolean objDelete = service.deleteById(9999999L);
		assertFalse(objDelete);
	}

	@Test
	public void deleteExistingObject() {
		c_insertValid();
		booking = service.readAll().get(0);
		Boolean objDelete = service.deleteById(booking.getId());
		assertTrue(objDelete);
	}

	@Test(expected = NoSuchElementException.class)
	public void readNonExistingObject() {
		booking = service.readById(9999999L);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void readAllEmptyTable() {
		booking = service.readAll().get(0);
	}

	@Test
	public void readExistingObject() {
		c_insertValid();
		booking = service.readAll().get(0);
		booking = service.readById(booking.getId());
		assertNotNull(booking);
	}

	@After // méthode sera appelé après chaque méthode de tests
	public void after() {
		System.out.println("************************DEBUG TESTING AfterMethod***********************");
		if (booking != null && booking.getId() != null) {
			service.deleteById(booking.getId());
		}
	}
}
