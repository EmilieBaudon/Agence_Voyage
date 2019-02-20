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

	private Logger log = Logger.getLogger(ActivityService.class);
	/**
	 * @param StandingService is an object from the Service layer, used to interact
	 *                        with the SQL database.
	 */

	@Autowired
	private StandingService service;
	private HotelService serviceH;

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
	 * 
	 *         The 'Hotel' object is first created, as a hotel is first required to
	 *         exist before a 'Standiing' object is created in the database.
	 */
	@RequestMapping(path = "create", method = RequestMethod.POST)
	public String createObject(@RequestBody StandingDtoWithId dtoId) {
		Hotel hotel = serviceH.create(new Hotel("Name","Descri",null,null));
		System.out.println(hotel.getId()+hotel.getName());
		hotel.setId(dtoId.getHotelDto().getId()); // On ne prend que l'ID car SQL n'a besoin que de l'ID pour
													// reconnaitre l'Hotel.

		Standing standing = service.create(new Standing(dtoId.getNbRoom(), dtoId.getPriceChild(), dtoId.getPriceAdult(),
				dtoId.getDesc(), hotel, null));

		if (standing != null) {
			log.info("Standing created (controller)");
			return "Standing created";
		} else {
			log.error("There was a problem creating your Standing (controller)");
			return "Standing not created";
		}
	}

	/**
	 * @Method updateObject is a method which allows the user to update an existing
	 *         Standing object in the database.
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
	 * @Method read is a method which allows the user to get information about an
	 *         existing Standing object in the database.
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
	 * @Method readall is a method which allows the user to get information about
	 *         all the Standing objects in the database.
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
	 * @Method delete is a method which allows the user to delete an existing
	 *         Standing object in the database.
	 */
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public String delete(Long id) {
		service.deleteById(id);

		if (service.readById(id).equals(null)) {
			log.info("Your Standing was deleted (controller)");
			return "Standing deleted";
		} else {
			log.error("There was an issue deleting your Standing (controller)");
			return "Standing NOT deleted";
		}
	}
}
