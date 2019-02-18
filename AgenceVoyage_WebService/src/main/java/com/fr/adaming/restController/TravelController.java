package com.fr.adaming.restController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.Service.TravelService;
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
public class TravelController implements IController<Travel> {

	@Autowired
	private TravelService service;

	private Travel createdTravel;

	@Override
	@RequestMapping(path = "create", method = RequestMethod.POST)
	public String createObject(@RequestBody Travel obj) {

		createdTravel = new Travel(5, "Bora-Bora", LocalDate.of(2018, 06, 23), LocalDate.of(2018, 06, 28), null, null,
				null);
		createdTravel = service.create(createdTravel);

		if (createdTravel != null) {

			return "Travel has been created";
		}

		else {
			return "Travel has not been created";
		}
	}

	@Override
	@RequestMapping(path = "update", method = RequestMethod.POST)
	public String updateObject(@RequestBody Travel obj) {
		createdTravel = service.update(createdTravel);

		if (createdTravel != null) {
			return "Travel has been created";
		} else {
			return "Travel has not been created";
		}

	}

	@Override
	@RequestMapping(path = "read/{id}", method = RequestMethod.GET)
	public Travel readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Travel> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
