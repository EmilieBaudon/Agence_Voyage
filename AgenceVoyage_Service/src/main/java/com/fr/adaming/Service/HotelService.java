package com.fr.adaming.Service;

import java.util.List;

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

	/**
	 * @param Data access object of the hotel
	 */
	@Autowired
	private IHotelDao dao;

	/**
	 * @method create an hotel in the database
	 * the creation is done only if the id of the object us null or equal to 0
	 */
	@Override
	public Hotel create(Hotel hotel) {
		if (hotel.getId() == null || hotel.getId() == 0L) {
			return dao.save(hotel);
		} else {
			return null;
		}
	}

	/**
	 * @method update an hotel in the database
	 * the update is done only if the id of the hotel is found in the DB
	 */
	@Override
	public Hotel update(Hotel hotel) {
		if (hotel.getId() != null && hotel.getId() != 0L && dao.existsById(hotel.getId())) {
			return dao.save(hotel);
		} else {
			return null;
		}
	}

	/**
	 * @method read an hotel in the database thanks to the id put in the parameter
	 */
	@Override
	public Hotel readById(Long id) {
		return dao.findById(id).get();
	}

	/**
	 * @method delete an hotel in the database thanks to the id put in the parameter
	 */
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	/**
	 * @method read all the hotels in the DB
	 */
	@Override
	public List<Hotel> readAll() {
		return dao.findAll();
	}
}