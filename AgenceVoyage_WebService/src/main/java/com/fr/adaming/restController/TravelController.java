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
import com.fr.adaming.dto.TravelDtoWithId;
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
public class TravelController implements IController<TravelDto, TravelDtoWithId> {

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
				dto.getPeriodEnd(), null, null, null));

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
	@RequestMapping(path = "update", method = RequestMethod.PUT)
	public String updateObject(@RequestBody TravelDtoWithId dto) {
		Travel travel = new Travel(dto.getNbrNight(), dto.getDestination(), dto.getPeriodBegin(), dto.getPeriodEnd(),
				null, null, null);
		travel.setId(dto.getId());
		service.update(travel);
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
	public TravelDtoWithId readById(@PathVariable(value = "id") Long id) {

		Travel result = service.readById(id);
		TravelDtoWithId dto = new TravelDtoWithId(result.getId(), result.getNbrNight(), result.getDestination(),
				result.getPeriodBegin(), result.getPeriodEnd(), null, null, null);
		return dto;
	}

	/**
	 * @Method readAll read all travels in database
	 */
	@Override
	@RequestMapping(path = "readall", method = RequestMethod.GET)
	public List<TravelDtoWithId> readAll() {
		List<Travel> result = service.readAll();
		List<TravelDtoWithId> listDto = new ArrayList<TravelDtoWithId>();
		for (Travel temp : result) {
			listDto.add(new TravelDtoWithId(temp.getId(), temp.getNbrNight(), temp.getDestination(),
					temp.getPeriodBegin(), temp.getPeriodEnd(), null, null, null));
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
