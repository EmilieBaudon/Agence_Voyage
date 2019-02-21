package com.fr.adaming.Service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IBookingDao;
import com.fr.adaming.entity.Booking;
import com.fr.adaming.entity.Person;
import com.fr.adaming.entity.Travel;

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

	private Logger log = Logger.getLogger(ActivityService.class);
	/**
	 * @param Data access object of the booking
	 */
	@Autowired
	private IBookingDao dao;
	/**
	 * @param service from person
	 */
	@Autowired
	private PersonService serviceP;
	/**
	 * @param service from travel
	 */
	@Autowired
	private TravelService serviceT;

	/**
	 * This method create an booking in the database the creation is done only if
	 * the id of the object us null or equal to 0
	 * 
	 * @return an object booking
	 * 
	 * @param an object booking
	 */
	@Override
	public Booking create(Booking booking) {
		if (booking.getId() == null || booking.getId() == 0L) {
			System.out.println(booking.getCustomer().getId());

			System.out.println("DEBUGG :: travel is " + booking.getTravel());

			Person pers = serviceP.readById(booking.getCustomer().getId());
			Travel trav = serviceT.readById(booking.getTravel().getId());

			if (pers != null && trav != null) {
				log.info("Booking created (service)");
				return dao.save(booking);
			} else {
				log.error("There was a problem creating your Standing (service)");
				return null;
			}
		} else {
			return null;
		}

	}

	/**
	 * This method update an booking in the database the update is done only if the
	 * id of the booking is found in the DB
	 * 
	 * @return an object booking
	 * 
	 * @param an object booking
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
	 * This method read an booking in the database thanks to the id put in the
	 * parameter
	 * 
	 * @return an object booking
	 * 
	 * @param a Long id representing the activity id
	 */
	@Override
	public Booking readById(Long id) {
		try {
			Booking booking = dao.findById(id).get();
			log.info("read by id done in service");
			return booking;
		} catch (Exception e) {
			log.error("This id does not exist");
			return null;
		}
	}

	/**
	 * This method delete an booking in the database thanks to the id put in the
	 * parameter
	 * 
	 * @param a Long id representing the flight id
	 * @return a boolean, true if the delete happened and false otherwise
	 * 
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
	 * This method read all the bookings in the DB
	 * 
	 * @return a list of booking from the database
	 * 
	 */
	@Override
	public List<Booking> readAll() {
		List<Booking> list = dao.findAll();
		if (list.isEmpty()) {
			log.info("read all done in service");
			return list;
		} else {
			log.warn("database is empty");
			return null;
		}
	}

}
