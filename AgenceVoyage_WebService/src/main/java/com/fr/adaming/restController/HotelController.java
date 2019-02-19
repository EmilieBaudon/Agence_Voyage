package com.fr.adaming.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	/**
	 * @param HotelService is an object from the Service layer, used to interact with the SQL database.
	 */
	
	@Autowired
	private HotelService service;

	/**
	 * @param HotelDto is an object used for Data transfer from the datapase to the user, using the RestController.  
	 */
	private HotelDto dto;

	
	/**
	 * @Method createObject is a method which allows the user to create a Hotel object in the database.
	 */
	@Override
	@RequestMapping(path = "create", method = RequestMethod.POST)
	public String createObject(@RequestBody HotelDto dto) {
		Hotel hotel = service.create(new Hotel(dto.getName(), dto.getDesc(), null, null));

		if (hotel != null) {
			return "Hotel created";
		} else {
			return "Hotel not created";
		}

	}
	
	
	/**
	 * @Method updateObject is a method which allows the user to update an existing Hotel object in the database.
	 */
	@Override
	@RequestMapping(path = "update", method = RequestMethod.POST)
	public String updateObject(@RequestBody HotelDtoWithId dto) {
		Hotel hotel = service.create(new Hotel(dto.getName(), dto.getDesc(), null, null));
		hotel.setId(dto.getId());
		if (hotel != null) {
			return "Hotel updated";
		} else {
			return "Hotel not updated";
		}

	}

	/**
	 * @Method read is a method which allows the user to get information about an existing Hotel object in the database.
	 */
	@Override
	@RequestMapping(path = "read/{id}", method = RequestMethod.GET)
	public HotelDtoWithId readById(@PathVariable(value = "id") Long id) {
		
		Hotel result = service.readById(id);
		HotelDtoWithId dto = new HotelDtoWithId(result.getId(),result.getName(), result.getDesc(), null, null);
		
		return dto;
	}

	/**
	 * @Method readall is a method which allows the user to get information about all the Hotel objects in the database.
	 */
	@Override
	@RequestMapping(path = "readall", method = RequestMethod.GET)
	public List<HotelDtoWithId> readAll() {
		List<Hotel> result = service.readAll();
		List<HotelDtoWithId> listDto = new ArrayList<HotelDtoWithId>();
		for (Hotel temp : result) {
			listDto.add(new HotelDtoWithId(temp.getId(),temp.getName(), temp.getDesc(), null, null));
		}
		
		return listDto;
	}
	
	/**
	 * @Method delete is a method which allows the user to delete an existing Hotel object in the database.
	 */
	@Override
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public String delete(Long id) {
		service.deleteById(id);
		
		return "Hotel deleted";
	}

	

}
