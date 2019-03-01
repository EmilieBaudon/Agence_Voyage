package com.fr.adaming.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.ActivityDto;
import com.fr.adaming.dto.HotelDtoWithId;
import com.fr.adaming.dto.StandingDto;
import com.fr.adaming.dto.StandingDtoWithId;
import com.fr.adaming.entity.Activity;
import com.fr.adaming.entity.Hotel;
import com.fr.adaming.entity.Standing;
import com.fr.adaming.service.ActivityService;
import com.fr.adaming.service.StandingService;

/**
 * 
 * This rest Controller treats data concerning Standings.
 * 
 * @author Quentin
 *
 */

@RestController
@RequestMapping(path = "standing/")
@CrossOrigin
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

	/**
	 * @param StandingDtoWithId is an object used for Data transfer from the
	 *                          database to the user, using the RestController. It
	 *                          has an "Id" attribute so that it can be used with
	 *                          the "Update", "Delete" and "readById" methods.
	 * 
	 */

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
	@PostMapping(path = "create")
	public String createObject(@RequestBody StandingDto dtoId) {

		Hotel hotel = new Hotel();
		hotel.setId(dtoId.getIdhotelDto()); // On ne prend que l'ID car SQL n'a besoin que de l'ID pour
											// reconnaitre l'Hotel.

		Standing stand = new Standing(dtoId.getNbRoom(), dtoId.getPriceChild(), dtoId.getPriceAdult(), dtoId.getDesc(),
				hotel, null);

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
	@PutMapping(path = "update")
	public String updateObject(@RequestBody StandingDtoWithId dtoId) {
		Hotel hotel = new Hotel();
		hotel.setId(dtoId.getHotelDto().getId());

		Standing standing = new Standing(dtoId.getNbRoom(), dtoId.getPriceChild(), dtoId.getPriceAdult(),
				dtoId.getDesc(), hotel, null);
		standing.setId(dtoId.getId());
		service.update(standing);

		if (standing == new Standing()) {

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
	@GetMapping(path = "read/{id}")
	public StandingDtoWithId readById(@PathVariable(value = "id") Long id) {
		Standing result = service.readById(id);
		List<ActivityDto> listActivity = new ArrayList<>();
		for (Activity activity : result.getLactivity()) {
			listActivity.add(new ActivityDto(activity.getId(), activity.getName(), activity.getDesc()));
		}
		StandingDtoWithId dtoId = new StandingDtoWithId(result.getId(), result.getNbRoom(), result.getPriceChild(),
				result.getPriceAdult(), result.getDesc(), new HotelDtoWithId(result.getHotel().getId(),
						result.getHotel().getName(), result.getHotel().getDesc(), null, null),
				listActivity);

		if (dtoId == new StandingDtoWithId()) {
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
	@GetMapping(path = "readall")
	public List<StandingDtoWithId> readAll() {
		List<Standing> result = service.readAll();
		List<StandingDtoWithId> listDto = new ArrayList<>();
		for (Standing temp : result) {
			List<ActivityDto> listActivity = new ArrayList<>();
			for (Activity activity : temp.getLactivity()) {
				listActivity.add(new ActivityDto(activity.getId(), activity.getName(), activity.getDesc()));
			}
			listDto.add(new StandingDtoWithId(temp.getId(), temp.getNbRoom(), temp.getPriceChild(),
					temp.getPriceAdult(), temp.getDesc(), new HotelDtoWithId(temp.getHotel().getId(),
							temp.getHotel().getName(), temp.getHotel().getDesc(), null, null),
					null));
		}
		if (listDto.isEmpty()) {
			log.error("There was an issue reading all your Standings (controller)");
			return listDto;
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
	@DeleteMapping(path = "delete/{id}")
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
