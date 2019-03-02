package com.fr.adaming.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.FlightDto;
import com.fr.adaming.dto.FlightDtoWithId;
import com.fr.adaming.entity.Flight;
import com.fr.adaming.service.ActivityService;
import com.fr.adaming.service.FlightService;

/**
 * @author Alan The controller for all the flight objects. Allow to take in
 *         consideration requests from the web
 */
@RestController
@RequestMapping(path = "flight/")
@CrossOrigin
public class FlightController {

	/**
	 * @param FlightService is an object used to access the database
	 */
	@Autowired
	private FlightService service;

	/**
	 * @param log is an object used to create logs
	 */
	private Logger log = Logger.getLogger(ActivityService.class);

	/**
	 * This method allows to create a flight with a the request method post
	 * 
	 * @param obj FlightDto an object Flight for the data transfert createObject is
	 *            here to create an object in the database with the parameter
	 * @return a String to signify if the method has worked
	 */
	@PostMapping(path = "create")
	public Flight createObject(@RequestBody FlightDto obj) {
		Flight result = service.create(new Flight(obj.getIdPlane(), obj.getDateArrival(), obj.getDateDeparture(),
				obj.getAirportDeparture(), obj.getAirportArrival(), obj.getPrice()));
		if (result != null) {
			log.info("flight created (controller)");
			return result;
		} else {
			log.warn("flight has not been created (controller)");
			return null;
		}
	}

	/**
	 * 
	 * This method allows to update a flight with a the request method post
	 * 
	 * @param obj FlightDto an object Flight for the data transfert updateObject is
	 *            here to update an object in the database with the parameter
	 * @return a String to signify if the method has worked
	 */
	@PutMapping(path = "update")
	public String updateObject(@RequestBody FlightDtoWithId obj) {

		Flight result = service.create(new Flight(obj.getIdPlane(), obj.getDateArrival(), obj.getDateDeparture(),
				obj.getAirportDeparture(), obj.getAirportArrival(), obj.getPrice()));
		if (result != null) {
			log.info("flight updated (controller)");
			return "A flight has been update";
		} else {
			log.warn("flight has not been updated (controller)");
			return "A problem has occurred ";
		}
	}

	/**
	 * This method allows to read a flight with his id
	 * 
	 * @param id is the id of the object we want to read readById is here to recover
	 *           an object in the database thanks the parameter id
	 * @return the flight with the id or null if it does not exist
	 */
	@GetMapping(path = "read/{id}")
	public FlightDtoWithId readById(Long id) {
		Flight result = service.readById(id);

		FlightDtoWithId dto = new FlightDtoWithId(result.getId(), result.getIdPlane(), result.getDateArrival(),
				result.getDateDeparture(), result.getAirportDeparture(), result.getAirportArrival(), result.getPrice());
		log.info("flight with id=" + id + " has been created (controller)");
		return dto;
	}

	/**
	 * readAll recover every flights in a table
	 * 
	 * @return a list with all the flight of the database
	 */
	@GetMapping(path = "readall")
	public List<FlightDtoWithId> readAll() {
		List<Flight> result = service.readAll();
		List<FlightDtoWithId> listDto = new ArrayList<>();
		for (Flight temp : result) {

			FlightDtoWithId dto = new FlightDtoWithId(temp.getId(), temp.getIdPlane(), temp.getDateArrival(),
					temp.getDateDeparture(), temp.getAirportDeparture(), temp.getAirportArrival(), temp.getPrice());
			listDto.add(dto);
		}
		log.info("All flight have been created (controller)");
		return listDto;
	}

	/**
	 * readAll recover every flights in a table
	 * 
	 * @param id of the flight
	 * @return a String to signify if the method has worked
	 */
	@DeleteMapping(path = "delete/{id}")
	public String delete(Long id) {
		if (service.deleteById(id)) {
			log.info("Flight deleted (controller)");
			return "Flight has been deleted";
		} else {
			log.error("Exception detected (controller)");
			return "Can't delete ! ";
		}
	}
}
