package com.fr.adaming.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.TravelDto;
import com.fr.adaming.dto.TravelDtoWithId;
import com.fr.adaming.entity.Hotel;
import com.fr.adaming.entity.Travel;
import com.fr.adaming.service.ActivityService;
import com.fr.adaming.service.TravelService;

/**
 * 
 * This Controller checks methods
 *
 *
 * @author Nicolas
 * 
 * 
 */

@RestController
@RequestMapping(path = "travel/")
public class TravelController implements IController<TravelDto, TravelDtoWithId> {

	/**
	 * @param service TravelService is an object used to access the database
	 */
	@Autowired
	private TravelService service;

	private Logger log = Logger.getLogger(ActivityService.class);

	/**
	 * CreateObject create an object in database with the parameter
	 * 
	 * @param dto is a data transfer object representing the service
	 * @return a string saying if the creation has been successful
	 */
	@Override
	@PostMapping(path = "create")
	public String createObject(@Valid @RequestBody TravelDto dto) {
		Hotel hotel = new Hotel();
		hotel.setId(dto.getId_hotelDto());

		Travel travel = service.create(new Travel(dto.getNbrNight(), dto.getDestination(), dto.getPeriodBegin(),
				dto.getPeriodEnd(), null, null, hotel));

		if (travel != null) {
			log.info("Travel created (controller)");
			return "Travel has been created";
		}

		else {
			log.warn("The travel you want to create has an id which already exist (controller)");
			return "Travel has not been created";

		}
	}

	/**
	 * UpdateObject update an object in database
	 * 
	 * @param dto is a data transfer object representing the service
	 * @return a string saying if the updating has been successful
	 */
	@Override
	@PutMapping(path = "update")
	public String updateObject(@Valid @RequestBody TravelDtoWithId dto) {
		Travel travel = new Travel(dto.getNbrNight(), dto.getDestination(), dto.getPeriodBegin(), dto.getPeriodEnd(),
				null, null, null);
		travel.setId(dto.getId());
		service.update(travel);
		if (travel.equals(null)) {
			log.warn("The travel you want to update has an id which already exist (controller)");
			return "Travel has not been updated";
		}

		else {
			log.info("Travel updated (controller)");
			return "Travel has been updated";
		}
	}

	/**
	 * ReadById read by id an object in database
	 * 
	 * @param id is the id of TravelDtoWithId
	 * @return a dto object
	 */
	@Override
	@GetMapping(path = "read/{id}")
	public TravelDtoWithId readById(@PathVariable(value = "id") Long id) {

		Travel result = service.readById(id);
		TravelDtoWithId dto = new TravelDtoWithId(result.getId(), result.getNbrNight(), result.getDestination(),
				result.getPeriodBegin(), result.getPeriodEnd(), null, null, null);
		log.info("Travel print (controller)");
		return dto;
	}

	/**
	 * ReadAll read all travels in database
	 * 
	 * @return an array list of TravelDtoWithId
	 */
	@Override
	@GetMapping(path = "readall")
	public List<TravelDtoWithId> readAll() {
		List<Travel> result = service.readAll();
		List<TravelDtoWithId> listDto = new ArrayList<>();
		for (Travel temp : result) {
			listDto.add(new TravelDtoWithId(temp.getId(), temp.getNbrNight(), temp.getDestination(),
					temp.getPeriodBegin(), temp.getPeriodEnd(), null, null, null));
		}
		log.info("List of travels printed (controller)");
		return listDto;

	}

	/**
	 * Delete delete an object with the id
	 * 
	 * @param id of the object that must be deleted
	 * @return a string saying if the delete has been successful
	 */
	@Override
	@DeleteMapping(path = "delete/{id}")
	public String delete(Long id) {
		if (service.deleteById(id)) {
			log.info("Travel deleted (controller)");
			return "Travel has been deleted";
		} else {
			log.error("Exception detected (controller)");
			return "Can't delete ! ";
		}
	}
}
