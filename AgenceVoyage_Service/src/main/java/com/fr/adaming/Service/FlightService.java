package com.fr.adaming.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IFlightDao;
import com.fr.adaming.entity.Flight;

/**
 * This class is the service part of the application for the flight model It
 * allows to make many interaction with the DB, create, update, read, delete an
 * flight and read all flights
 * 
 * @author Alan
 *
 */
@Service
public class FlightService implements IService<Flight> {

	/**
	 * @param Data access object of the flight
	 */
	@Autowired
	private IFlightDao dao;

	/**
	 * @method create an flight in the database the creation is done only if the id
	 *         of the object us null or equal to 0
	 */
	@Override
	public Flight create(Flight flight) {
		if (flight.getId() == null || flight.getId() == 0L) {
			return dao.save(flight);
		} else {
			return null;
		}
	}

	/**
	 * @method update an flight in the database the update is done only if the id
	 *         of the flight is found in the DB
	 */
	@Override
	public Flight update(Flight flight) {
		if (flight.getId() != null && flight.getId() != 0L && dao.existsById(flight.getId())) {
			return dao.save(flight);
		} else {
			return null;
		}
	}

	/**
	 * @method read an flight in the database thanks to the id put in the parameter
	 */
	@Override
	public Flight readById(Long id) {
		return dao.findById(id).get();
	}

	/**
	 * @method delete an flight in the database thanks to the id put in the
	 *         parameter
	 */
	public Boolean deleteById(Long id) {
		try {
			dao.deleteById(id);
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @method read all the flights in the DB
	 */
	@Override
	public List<Flight> readAll() {
		return dao.findAll();
	}
}