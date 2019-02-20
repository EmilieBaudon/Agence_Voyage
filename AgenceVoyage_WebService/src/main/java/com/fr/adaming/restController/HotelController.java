package com.fr.adaming.restController;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.Service.ActivityService;
import com.fr.adaming.Service.HotelService;
import com.fr.adaming.dto.HotelDto;
import com.fr.adaming.dto.HotelDtoWithId;
import com.fr.adaming.entity.Hotel;

/**
 * 
 * This rest Controller treats data concerning Hotels.
 * 
 * @author Quentin
 *
 */

@RestController
@RequestMapping(path = "hotel/")
public class HotelController implements IController<HotelDto, HotelDtoWithId> {

	private Logger log = Logger.getLogger(ActivityService.class);
	/**
	 * @param HotelService is an object from the Service layer, used to interact
	 *                     with the SQL database.
	 */

	@Autowired

	private HotelService service;

	/**
	 * @param HotelDto is an object used for Data transfer from the database to the
	 *                 user, using the RestController.
	 */
	private HotelDto dto;

	/**
	 * @param HotelDtoWithId is an object used for Data transfer from the database
	 *                       to the user, using the RestController. It has an "Id"
	 *                       attribute so that it can be used with the "Update",
	 *                       "Delete" and "readById" methods.
	 * 
	 */

	private HotelDtoWithId dtoId;

	/**
	 * @Method createObject is a method which allows the user to create a Hotel
	 *         object in the database.
	 */
	@Override
	@RequestMapping(path = "create", method = RequestMethod.POST)
	public String createObject(@RequestBody HotelDto dto) {
		Hotel hotel = service.create(new Hotel(dto.getName(), dto.getDesc(), null, null));

		if (hotel != null) {
			log.info("Hotel created (controller)");
			return "Hotel created";
		} else {
			log.error("There was a problem creating your hotel (controller)");
			return "Hotel not created";
		}
	}

	/**
	 * @Method updateObject is a method which allows the user to update an existing
	 *         Hotel object in the database.
	 */
	@Override
	@RequestMapping(path = "update", method = RequestMethod.POST)
	public String updateObject(@RequestBody HotelDtoWithId dto) {
		Hotel hotel = new Hotel(dto.getName(), dto.getDesc(), null, null);
		hotel.setId(dto.getId());
		service.update(hotel);
		if (hotel.equals(null)) {

			log.error("There was a problem updting your Hotel (controller)");
			return "Hotel not updated";
		} else {

			log.info("Hotel updated (controller)");
			return "Hotel updated";
		}

	}

	/**
	 * @Method read is a method which allows the user to get information about an
	 *         existing Hotel object in the database.
	 */
	@Override
	@RequestMapping(path = "read/{id}", method = RequestMethod.GET)
	public HotelDtoWithId readById(@PathVariable(value = "id") Long id) {

		Hotel result = service.readById(id);
		HotelDtoWithId dto = new HotelDtoWithId(result.getId(), result.getName(), result.getDesc(), null, null);

		if (dto.equals(null)) {
			log.error("There was an issue reading your Hotel (controller)");
			return null;
		} else {
			log.info("Your Hotel : (controller)");
			return dto;
		}

	}

	/**
	 * @Method readall is a method which allows the user to get information about
	 *         all the Hotel objects in the database.
	 */
	@Override
	@RequestMapping(path = "readall", method = RequestMethod.GET)
	public List<HotelDtoWithId> readAll() {
		List<Hotel> result = service.readAll();
		List<HotelDtoWithId> listDto = new ArrayList<HotelDtoWithId>();
		for (Hotel temp : result) {
			listDto.add(new HotelDtoWithId(temp.getId(), temp.getName(), temp.getDesc(), null, null));
		}

		if (listDto.equals(null)) {
			log.error("There was an issue reading all your Hotel (controller)");
			return null;
		} else {
			log.info("Your Hotel List: (controller)");
			return listDto;
		}

	}

	/**
	 * @Method delete is a method which allows the user to delete an existing Hotel
	 *         object in the database.
	 */
	@Override
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public String delete(Long id) {
		service.deleteById(id);

		if (service.deleteById(id) == true) {
			log.info("Your Hotel was deleted (controller)");
			return "Hotel deleted";
		} else {
			log.error("There was an issue deleting your Hotel (controller)");
			return "Hotel NOT deleted";
		}
	}
}
