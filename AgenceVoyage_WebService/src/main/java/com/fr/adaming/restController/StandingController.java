package com.fr.adaming.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.Service.StandingService;
import com.fr.adaming.dto.StandingDto;
import com.fr.adaming.dto.StandingDtoWithId;
import com.fr.adaming.entity.Standing;

/**
 * 
 * This rest Controller treats data concerning Standings.
 * 
 * @author Quentin
 *
 */

@RestController
@RequestMapping(path = "standing/")
public class StandingController implements IController<StandingDto, StandingDtoWithId> {

	/**
	 * @param StandingService is an object from the Service layer, used to interact
	 *                        with the SQL database.
	 */

	@Autowired
	private StandingService service;

	/**
	 * @param StandingDto is an object used for Data transfer from the database to
	 *                    the user, using the RestController. It does not have an
	 *                    "Id" attribute.
	 */

	private StandingDto dto;

	/**
	 * @param StandingDtoWithId is an object used for Data transfer from the
	 *                          database to the user, using the RestController. It
	 *                          has an "Id" attribute so that it can be used with
	 *                          the "Update", "Delete" and "readById" methods.
	 * 
	 */

	private StandingDtoWithId dtoId;

	/**
	 * @Method createObject is a method which allows the user to create a Standing
	 *         object in the database.
	 */
	@Override
	@RequestMapping(path = "create", method = RequestMethod.POST)
	public String createObject(@RequestBody StandingDto dto) {
		Standing standing = service.create(
				new Standing(dto.getNbRoom(), dto.getPriceChild(), dto.getPriceAdult(), dto.getDesc(), null, null));

		if (standing != null) {
			return "Standing created";
		} else {
			return "Standing not created";
		}
	}

	/**
	 * @Method updateObject is a method which allows the user to update an existing
	 *         Standing object in the database.
	 */
	@Override
	@RequestMapping(path = "update", method = RequestMethod.POST)
	public String updateObject(@RequestBody StandingDtoWithId dtoId) {
		Standing standing = new Standing(dtoId.getNbRoom(), dtoId.getPriceChild(), dtoId.getPriceAdult(),
				dtoId.getDesc(), null, null);
		standing.setId(dtoId.getId());
		service.update(standing);
		if (standing != null) {
			return "Standing updated";
		} else {
			return "Standing not updated";
		}
	}

	/**
	 * @Method read is a method which allows the user to get information about an
	 *         existing Standing object in the database.
	 */
	@Override
	@RequestMapping(path = "read/{id}", method = RequestMethod.GET)
	public StandingDtoWithId readById(@PathVariable(value = "id") Long id) {
		Standing result = service.readById(id);
		StandingDtoWithId dtoId = new StandingDtoWithId(result.getId(), result.getNbRoom(), result.getPriceChild(),
				result.getPriceAdult(), result.getDesc(), null, null);

		return dtoId;
	}

	/**
	 * @Method readall is a method which allows the user to get information about
	 *         all the Standing objects in the database.
	 */
	@Override
	@RequestMapping(path = "readall", method = RequestMethod.GET)
	public List<StandingDtoWithId> readAll() {
		List<Standing> result = service.readAll();
		List<StandingDtoWithId> listDto = new ArrayList<StandingDtoWithId>();
		for (Standing temp : result) {
			listDto.add(new StandingDtoWithId(temp.getId(), temp.getNbRoom(), temp.getPriceChild(),
					temp.getPriceAdult(), temp.getDesc(), null, null));
		}
		return listDto;
	}

	/**
	 * @Method delete is a method which allows the user to delete an existing Standing
	 *         object in the database.
	 */
	@Override
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public String delete(Long id) {
		service.deleteById(id);

		return "Standing deleted";
	}

}
