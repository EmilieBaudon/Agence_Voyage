package com.fr.adaming.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.Service.TravelService;
import com.fr.adaming.dto.TravelDto;
import com.fr.adaming.entity.Travel;

/**
 * 
 * This Controller checks methods
 *
 *
 * @author Nicolas
 */

@RestController
@RequestMapping(path = "travel/")
public class TravelController implements IController<TravelDto> {

	/**
	 * @param TravelService is an object used to access the database
	 */
	@Autowired
	private TravelService service;

	private TravelDto dto;

	/**
	 * @Method createObject create an object in database with the parameter
	 */
	@Override
	@RequestMapping(path = "create", method = RequestMethod.POST)
	public String createObject(@RequestBody TravelDto dto) {

		Travel travel = service.create(new Travel(dto.getNbrNight(), dto.getDestination(), dto.getPeriodBegin(),
				dto.getPeriodEnd(), dto.getLbookingDto(), dto.getLflightDto(), dto.getHotelDto()));

		if (travel != null) {

			return "Travel has been created";
		}

		else {
			return "Travel has not been created";
		}
	}

	/**
	 * @Method updateObject update an object in database
	 */
	@Override
	@RequestMapping(path = "update", method = RequestMethod.POST)
	public String updateObject(@RequestBody TravelDto obj) {
		Travel travel = service.update(new Travel(dto.getNbrNight(), dto.getDestination(), dto.getPeriodBegin(),
				dto.getPeriodEnd(), dto.getLbookingDto(), dto.getLflightDto(), dto.getHotelDto()));
		if (travel != null) {

			return "Travel has been updated";
		}

		else {
			return "Travel has not been updated";
		}
	}

	/**
	 * @Method readById read by id an object in database
	 */
	@Override
	@RequestMapping(path = "read/{id}", method = RequestMethod.GET)
	public TravelDto readById(@PathVariable(value = "id") Long id) {

		Travel result = service.readById(id);
		TravelDto dto = new TravelDto(result.getNbrNight(), result.getDestination(), result.getPeriodBegin(),
				result.getPeriodEnd(), result.getLbooking(), result.getLflight(), result.getLflight());
		return dto;
	}

	/**
	 * @Method readAll read all travels in database
	 */
	@Override
	@RequestMapping(path = "readall", method = RequestMethod.GET)
	public List<TravelDto> readAll() {
		List<Travel> result = service.readAll();
		List<TravelDto> listDto = new ArrayList<TravelDto>();
		for (Travel temp : result) {
			listDto.add(new TravelDto(temp.getNbrNight(), temp.getDestination(), temp.getPeriodBegin(), temp.getPeriodEnd(), temp.getLbooking(), temp.getLflight(), temp.getHotel());
		}
		return listDto;
		
	
	}

	/**
	 * @Method delete delete an object with the id
	 */
	@Override
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public String delete(Long id) {
		service.deleteById(id);
		return "A flight has been delete";
	}

}
