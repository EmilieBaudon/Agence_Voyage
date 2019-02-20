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

	/**
	 * @param object to generate log
	 */
	private Logger log = Logger.getLogger(ActivityService.class);

	/**
	 * This method create an flight in the database the creation is done only if the
	 * id of the object us null or equal to 0
	 * 
	 * @param flight an object flight to be created
	 * 
	 * @return the object flight created
	 */
	@Override
	public Flight create(Flight flight) {
		if ((flight.getId() == null || flight.getId() == 0L) && (flight.getTravel() != null)) {
			log.info("flight created (service)");
			return dao.save(flight);
		} else {
			log.warn("The flight you want to create has an id which already exist (service)");
			return null;
		}
	}

	/**
	 * This method update an flight in the database the update is done only if the id of
	 *         the flight is found in the DB
	 *         
	 * @param flight an object flight to be updated
	 * 
	 * @return the object updated
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
	 * This method read an flight in the database thanks to the id put in the parameter
	 * 
	 * @param id a Long id representing the flight id 
	 * 
	 * @return the object read
	 */
	@Override
	public Flight readById(Long id) {
		Flight flight = dao.findById(id).get();
		log.info("flight (service) with id=" + id + " has been read from the DB");
		return flight;
	}

	/**
	 * This method delete an flight in the database thanks to the id put in the
	 *         parameter
	 *         
	 * @param id a Long id representing the flight id 
	 * 
	 * @return the boolean object, return True if the object has been delete, false otherwise
	 */
	public Boolean deleteById(Long id) {
		try {
			dao.deleteById(id);
			log.info("Flight deleted (service)");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception detected (service)");
			return false;
		}
	}

	/**
	 * This method read all the flights in the DB
	 * 
	 * @return a list of flight return from the database 
	 */
	@Override
	public List<Flight> readAll() {
		List<Flight> list = dao.findAll();
		log.info("all flights (service) have been read from the DB");
		return list;
	}
}