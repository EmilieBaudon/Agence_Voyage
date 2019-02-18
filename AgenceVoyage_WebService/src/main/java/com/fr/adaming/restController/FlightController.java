package com.fr.adaming.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.Service.FlightService;
import com.fr.adaming.entity.Flight;

/**
 * @author Alan The controller for all the flight objects. Allow to take in
 *         consideration requests from the web
 */
@RestController
@RequestMapping(path = "flight/")
public class FlightController implements IController<Flight> {

	/**
	 * @param FlightService is an object used to access the database
	 */
	@Autowired
	private FlightService service;

	/**
	 * @param Flight an object Flight
	 * @method createObject is here to create an object in the database with the
	 *         parameter
	 */
	@RequestMapping(path = "create", method = RequestMethod.POST)
	public String createObject(@RequestBody Flight obj) {
		Flight result = service.create(obj);
		if (result != null) {
			return "A flight has been created";
		} else {
			return "A problem has occurred ";
		}
	}

	/**
	 * @param Flight an object Flight
	 * @method updateObject is here to update an object in the database with the
	 *         parameter
	 */
	@RequestMapping(path = "update", method = RequestMethod.POST)
	public String updateObject(Flight obj) {
		Flight result = service.update(obj);
		if (result != null) {
			return "A flight has been update";
		} else {
			return "A problem has occurred ";
		}
	}

	/**
	 * @param id is the id of the object we want to read
	 * @method readById is here to recover an object in the database thanks the
	 *         parameter id
	 */
	@RequestMapping(path = "read/{id}", method = RequestMethod.GET)
	public Flight readById(Long id) {
		return service.readById(id);
	}

	
	/**
	 * @method readAll
	 */
	@RequestMapping(path = "readall", method = RequestMethod.GET)
	public List<Flight> readAll() {
		return service.readAll();
	}

	@Override
	public String delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
