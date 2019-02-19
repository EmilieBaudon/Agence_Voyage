package com.fr.adaming.Service;

import java.util.List;

import org.apache.log4j.Logger;
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

	private Logger log = Logger.getLogger(ActivityService.class);

	/**
	 * @method create an flight in the database the creation is done only if the id
	 *         of the object us null or equal to 0
	 */
	@Override
	public Flight create(Flight flight) {
		if (flight.getDateDeparture().isBefore(flight.getDateArrival())) {
			return null;
		}
		if (flight.getId() == null || flight.getId() == 0L) {
			log.info("flight created (service)");
			return dao.save(flight);
		} else {
			log.warn("The flight you want to create has an id which already exist (service)");
			return null;
		}
	}

	/**
	 * @method update an flight in the database the update is done only if the id of
	 *         the flight is found in the DB
	 */
	@Override
	public Flight update(Flight flight) {
		if (flight.getId() != null && flight.getId() != 0L && dao.existsById(flight.getId())) {
			log.info("flight updated (service)");
			return dao.save(flight);
		} else {
			log.warn("The flight (service) you want to update has an id does not exist in the database(service)");
			return null;
		}
	}

	/**
	 * @method read an flight in the database thanks to the id put in the parameter
	 */
	@Override
	public Flight readById(Long id) {
		Flight flight = dao.findById(id).get();
		log.info("flight (service) with id=" + id +" has been read from the DB");
		return flight;
	}

	/**
	 * @method delete an flight in the database thanks to the id put in the
	 *         parameter
	 */
	public Boolean deleteById(Long id) {
		try {
			dao.deleteById(id);
			log.info("flight with id=" + id + " delete (service)");
			return true;
		} catch (Exception e) {
			log.error("flight (service) with id=" + id + " has not been delete: " + e.getMessage());
			return false;
		}
	}

	/**
	 * @method read all the flights in the DB
	 */
	@Override
	public List<Flight> readAll() {
		List<Flight> list = dao.findAll();
		log.info("all flights (service) have been read from the DB");
		return list;
	}
}