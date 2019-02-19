package com.fr.adaming.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.Service.FlightService;
import com.fr.adaming.dto.FlightDto;
import com.fr.adaming.dto.FlightDtoWithId;
import com.fr.adaming.dto.TravelDtoWithId;
import com.fr.adaming.entity.Flight;
import com.fr.adaming.entity.Travel;

/**
 * @author Alan The controller for all the flight objects. Allow to take in
 *         consideration requests from the web
 */
@RestController
@RequestMapping(path = "flight/")
public class FlightController implements IController<FlightDto,FlightDtoWithId> {

	/**
	 * @param FlightService is an object used to access the database
	 */
	@Autowired
	private FlightService service;

	/**
	 * @param FlightDto an object Flight for the data transfert
	 * @method createObject is here to create an object in the database with the
	 *         parameter
	 */
	@RequestMapping(path = "create", method = RequestMethod.POST)
	public String createObject(@RequestBody FlightDto obj) {
		Travel travel = new Travel(obj.getTravelDto().getNbrNight(),obj.getTravelDto().getDestination(), obj.getTravelDto().getPeriodBegin(),obj.getTravelDto().getPeriodEnd(), null, null, null);
		travel.setId(obj.getTravelDto().getId());
		Flight result = service.create(new Flight(obj.getIdPlane(), obj.getDateArrival(), obj.getDateDeparture(),
				obj.getAirportDeparture(), obj.getAirportArrival(), travel));
		if (result != null) {
			return "A flight has been created";
		} else {
			return "A problem has occurred ";
		}
	}

	/**
	 * @param FlightDto an object Flight for the data transfert
	 * @method updateObject is here to update an object in the database with the
	 *         parameter
	 */
	@RequestMapping(path = "update", method = RequestMethod.PUT)
	public String updateObject(@RequestBody FlightDtoWithId obj) {
		//Travel travel = new Travel(obj.getTravelDto().getNbrNight(),obj.getTravelDto().getDestination(), obj.getTravelDto().getPeriodBegin(),obj.getTravelDto().getPeriodEnd(), null, null, null);
		Travel travel = new Travel();
		travel.setId(obj.getTravelDto().getId());
		Flight result = service.create(new Flight(obj.getIdPlane(), obj.getDateArrival(), obj.getDateDeparture(),
				obj.getAirportDeparture(), obj.getAirportArrival(), travel));
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
	public FlightDtoWithId readById(Long id) {
		Flight result = service.readById(id);
		TravelDtoWithId tdto = new TravelDtoWithId(result.getTravel().getId(),result.getTravel().getNbrNight(), result.getTravel().getDestination(), result.getTravel().getPeriodBegin(), result.getTravel().getPeriodEnd(), null, null, null);
		FlightDtoWithId dto = new FlightDtoWithId(result.getId(),result.getIdPlane(), result.getDateArrival(), result.getDateDeparture(), result.getAirportDeparture(), result.getAirportArrival(),tdto);
		return dto;
	}

	/**
	 * @method readAll recover every flights in a table
	 */
	@RequestMapping(path = "readall", method = RequestMethod.GET)
	public List<FlightDtoWithId> readAll() {
		List<Flight> result = service.readAll();
		List<FlightDtoWithId> listDto = new ArrayList<FlightDtoWithId>();
		for (Flight temp : result) {
			TravelDtoWithId tdto = new TravelDtoWithId(temp.getTravel().getId(),temp.getTravel().getNbrNight(), temp.getTravel().getDestination(), temp.getTravel().getPeriodBegin(), temp.getTravel().getPeriodEnd(), null, null, null);
			FlightDtoWithId dto = new FlightDtoWithId(temp.getId(),temp.getIdPlane(), temp.getDateArrival(), temp.getDateDeparture(), temp.getAirportDeparture(), temp.getAirportArrival(),tdto);
			listDto.add(dto);
		}
		return listDto;
	}

	/**
	 * @method readAll recover every flights in a table
	 */
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public String delete(Long id) {
		service.deleteById(id);
		return "A flight has been delete";
	}

}
