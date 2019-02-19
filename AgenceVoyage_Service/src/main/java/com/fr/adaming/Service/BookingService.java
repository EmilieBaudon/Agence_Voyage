package com.fr.adaming.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IBookingDao;
import com.fr.adaming.entity.Booking;
import com.fr.adaming.entity.Flight;
import com.fr.adaming.entity.Standing;

/**
 * This class is the service part of the application for the booking model It
 * allows to make many interaction with the DB, create, update, read, delete an
 * booking and read all bookings
 * 
 * @author Alan
 *
 */
@Service
@Qualifier("BookingService")
public class BookingService implements IService<Booking> {

	/**
	 * @param Data access object of the booking
	 */
	@Autowired
	private IBookingDao dao;

	/**
	 * @method create an booking in the database the creation is done only if the id
	 *         of the object us null or equal to 0
	 */
	@Override
	public Booking create(Booking booking) {
		if (booking.getId() == null || booking.getId() == 0L) {
			return dao.save(booking);
		} else {
			return null;
		}
	}

	/**
	 * @method update an booking in the database the update is done only if the id
	 *         of the booking is found in the DB
	 */
	@Override
	public Booking update(Booking booking) {
		if (booking.getId() != null && booking.getId() != 0L && dao.existsById(booking.getId())) {
			return dao.save(booking);
		} else {
			return null;
		}
	}

	/**
	 * @method read an booking in the database thanks to the id put in the parameter
	 */
	@Override
	public Booking readById(Long id) {
		return dao.findById(id).get();
	}

	/**
	 * @method delete an booking in the database thanks to the id put in the
	 *         parameter
	 */
	public Boolean deleteById(Long id) {
		try {
			dao.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @method read all the bookings in the DB
	 */
	@Override
	public List<Booking> readAll() {
		return dao.findAll();
	}

	public Booking booking(Booking booking, Standing standing, Flight flight) {

		booking.setTotalPrice((booking.getNbrAdult() * (standing.getPriceAdult() + flight.getPrice()))
				+ (booking.getNbrChild() * (standing.getPriceChild() + flight.getPrice())));

		dao.save(booking);
		return booking;

	}
}
