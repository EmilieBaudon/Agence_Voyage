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
import com.fr.adaming.Service.StandingService;
import com.fr.adaming.dto.StandingDto;
import com.fr.adaming.dto.StandingDtoWithId;
import com.fr.adaming.entity.Hotel;
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
public class StandingController {

	/**
	 * @param log is an object used to create logs
	 */
	private Logger log = Logger.getLogger(ActivityService.class);
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
	 * createObject is a method which allows the user to create a Standing object in
	 * the database.
	 * 
	 * The 'Hotel' object is first created, as a hotel is first required to exist
	 * before a 'Standing' object is created in the database.
	 * 
	 * @param dtoId the parameter is a 'StandingDtoWithId' object
	 * @return the return is a String describing the status of the method outcome
	 */
	@RequestMapping(path = "create", method = RequestMethod.POST)
	public String createObject(@RequestBody StandingDto dtoId) {

		Hotel hotel = new Hotel();
		hotel.setId(dtoId.getId_hotelDto()); // On ne prend que l'ID car SQL n'a besoin que de l'ID pour
													// reconnaitre l'Hotel.
		System.out.println(hotel.getId());
		
		Standing stand = new Standing(dtoId.getNbRoom(), dtoId.getPriceChild(), dtoId.getPriceAdult(),
				dtoId.getDesc(), hotel, null);
		
		Standing standing = service.create(stand);

		if (standing != null) {
			log.info("Standing created (controller)");
			return "Standing created";
		} else {
			log.error("There was a problem creating your Standing (controller)");
			return "Standing not created";
		}
	}

	/**
	 * updateObject is a method which allows the user to update an existing Standing
	 * object in the database.
	 * 
	 * @param dtoId the parameter is a 'StandingDtoWithId' object
	 * @return the return is a String describing the status of the method outcome
	 */
	@RequestMapping(path = "update", method = RequestMethod.POST)
	public String updateObject(@RequestBody StandingDtoWithId dtoId) {
		Hotel hotel = new Hotel();
		hotel.setId(dtoId.getHotelDto().getId());

		Standing standing = new Standing(dtoId.getNbRoom(), dtoId.getPriceChild(), dtoId.getPriceAdult(),
				dtoId.getDesc(), hotel, null);
		standing.setId(dtoId.getId());
		service.update(standing);

		if (standing.equals(null)) {

			log.error("There was a problem updting your Standing (controller)");
			return "Standing not updated";
		} else {

			log.info("Standing updated (controller)");
			return "Standing updated";
		}
	}

	/**
	 * read is a method which allows the user to get information about an existing
	 * Standing object in the database.
	 * 
	 * @param id the parameter id is a Long attribute
	 * @return the return is a 'StandingDtoWithId' object
	 */
	@RequestMapping(path = "read/{id}", method = RequestMethod.GET)
	public StandingDtoWithId readById(@PathVariable(value = "id") Long id) {
		Standing result = service.readById(id);
		StandingDtoWithId dtoId = new StandingDtoWithId(result.getId(), result.getNbRoom(), result.getPriceChild(),
				result.getPriceAdult(), result.getDesc(), null, null);

		if (dtoId.equals(null)) {
			log.error("There was an issue reading your Standing (controller)");
			return null;
		} else {
			log.info("Your Standing : (controller)");
			return dtoId;
		}

	}

	/**
	 * readall is a method which allows the user to get information about all the
	 * Standing objects in the database.
	 * 
	 * @return the return is a 'StandingDtoWithId' object.
	 */
	@RequestMapping(path = "readall", method = RequestMethod.GET)
	public List<StandingDtoWithId> readAll() {
		List<Standing> result = service.readAll();
		List<StandingDtoWithId> listDto = new ArrayList<StandingDtoWithId>();
		for (Standing temp : result) {
			listDto.add(new StandingDtoWithId(temp.getId(), temp.getNbRoom(), temp.getPriceChild(),
					temp.getPriceAdult(), temp.getDesc(), null, null));
		}
		if (listDto.equals(null)) {
			log.error("There was an issue reading all your Standings (controller)");
			return null;
		} else {
			log.info("Your Standing List: (controller)");
			return listDto;
		}

	}

	/**
	 * delete is a method which allows the user to delete an existing Standing
	 * object in the database.
	 * 
	 * @param id is an Long attribute
	 * @return the return is a String describing the status of the method outcome
	 */
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public String delete(Long id) {
		

		if (service.deleteById(id)) {
			service.deleteById(id);
			log.info("Your Standing was deleted (controller)");
			return "Standing deleted";
		} else {
			log.error("There was an issue deleting your Standing (controller)");
			return "Standing NOT deleted";
		}
	}
}
