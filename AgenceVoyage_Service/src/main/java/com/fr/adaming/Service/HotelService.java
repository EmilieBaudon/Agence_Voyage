package com.fr.adaming.Service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IHotelDao;
import com.fr.adaming.entity.Hotel;

/**
 * This class is the service part of the application for the hotel model It
 * allows to make many interaction with the DB, create, update, read, delete an
 * hotel and read all hotels
 * 
 * @author Alan
 *
 */
@Service
public class HotelService implements IService<Hotel> {

	private Logger log = Logger.getLogger(ActivityService.class);

	/**
	 * @param Data access object of the hotel
	 */
	@Autowired
	private IHotelDao dao;

	/**
	 * This method create an hotel in the database the creation is done only if the id
	 *         of the object us null or equal to 0
	 * 
	 * @param hotel an object flight to be created
	 * 
	 * @return the object flight created
	 */
	@Override
	public Hotel create(Hotel hotel) {
		if (hotel.getId() == null || hotel.getId() == 0L) {
			log.info("Hotel created (service)");
			return dao.save(hotel);
		} else {
			log.error("There was a problem creating your hotel (service)");
			return null;
		}
	}

	/**
	 * This method update an hotel in the database the update is done only if the id of
	 *         the hotel is found in the DB
	 * 
	 * @param hotel an object flight to be updated
	 * 
	 * @return the object flight updated
	 */
	@Override
	public Hotel update(Hotel hotel) {
		if (hotel.getId() != null && hotel.getId() != 0L && dao.existsById(hotel.getId())) {
			log.info("Hotel updated (service)");
			return dao.save(hotel);

		} else {
			log.error("There was a problem updting your Hotel (service)");
			return null;
		}
	}

	/**
	 * This method read an hotel in the database thanks to the id put in the parameter
	 * 
	 * @param id a Long id representing the hotel id
	 * 
	 * @return the object read
	 */
	@Override
	public Hotel readById(Long id) {

		Hotel test = dao.findById(id).get();

		if (test.equals(null)) {
			log.error("There was an issue reading your Hotel (service)");
			return null;
		} else {
			log.info("Your Hotel : (service)");
			return test;
		}

	}

	/**
	 * This method delete an hotel in the database thanks to the id put in the parameter
	 * 
	 * @param id a Long id representing the hotel id 
	 * 
	 * @return the boolean object, return True if the object has been delete, false otherwise
	 */
	public Boolean deleteById(Long id) {
		try {
			dao.deleteById(id);
			log.info("Your Hotel was deleted (service)");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("There was an issue deleting your Hotel (service)");
			return false;
		}
	}

	/**
	 * This method read all the hotels in the DB
	 * 
	 * @return a list of hotels return from the database 
	 */
	@Override
	public List<Hotel> readAll() {

		List<Hotel> listH = dao.findAll();

		if (listH.equals(null)) {
			log.error("There was an issue reading all your Hotels (service)");
			return null;
		} else {
			log.info("Your Hotel List: (service)");
			return listH;
		}

	}
}