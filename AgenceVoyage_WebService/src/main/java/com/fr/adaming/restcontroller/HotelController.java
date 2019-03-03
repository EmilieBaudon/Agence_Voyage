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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.HotelDto;
import com.fr.adaming.dto.HotelDtoWithId;
import com.fr.adaming.dto.TravelDtoWithId;
import com.fr.adaming.entity.Hotel;
import com.fr.adaming.entity.Standing;
import com.fr.adaming.entity.Travel;
import com.fr.adaming.service.ActivityService;
import com.fr.adaming.service.HotelService;

/**
 * 
 * This rest Controller treats data concerning Hotels.
 * 
 * @author Quentin
 *
 */

@RestController
@RequestMapping(path = "hotel/")
@CrossOrigin
public class HotelController implements IController<HotelDto, HotelDtoWithId> {

	/**
	 * @param log is an object used to create logs
	 */
	private Logger log = Logger.getLogger(ActivityService.class);
	/**
	 * @param HotelService is an object from the Service layer, used to interact
	 *                     with the SQL database.
	 */

	@Autowired

	private HotelService service;

	/**
	 * @param dto HotelDto is an object used for Data transfer from the database to
	 *            the user, using the RestController.
	 */

	/**
	 * @param dtoId HotelDtoWithId is an object used for Data transfer from the
	 *              database to the user, using the RestController. It has an "Id"
	 *              attribute so that it can be used with the "Update", "Delete" and
	 *              "readById" methods.
	 * 
	 */

	/**
	 * createObject is a method which allows the user to create a Hotel object in
	 * the database.
	 * 
	 * @param dto the parameter is a 'HotelDto' object
	 * @return the return is a String describing the status of the method outcome
	 */
	@Override
	@PostMapping(path = "create")
	public String createObject(@RequestBody HotelDto dto) {
		Standing standing = new Standing();
		standing.setId(dto.getIdStanding());

		Hotel hotel = service.create(new Hotel(dto.getName(), dto.getDesc(), null, standing));

		if (hotel != null) {
			log.info("Hotel created (controller)");
			return "Hotel created";
		} else {
			log.error("There was a problem creating your hotel (controller)");
			return "Hotel not created";
		}
	}

	/**
	 * updateObject is a method which allows the user to update an existing Hotel
	 * object in the database.
	 * 
	 * @param dto the parameter is a 'HotelDtoWithId' object
	 * @return the return is a String describing the status of the method outcome
	 */
	@Override
	@PostMapping(path = "update")
	public String updateObject(@RequestBody HotelDtoWithId dto) {
		Standing standing = new Standing(dto.getLstandingDto().getNbRoom(), dto.getLstandingDto().getPriceChild(), dto.getLstandingDto().getPriceAdult(), dto.getLstandingDto().getDesc(), dto.getLstandingDto().getLactivity());
		standing.setId(dto.getLstandingDto().getId());
		
		
		Hotel hotel = new Hotel(dto.getName(), dto.getDesc(), null, standing);
		hotel.setId(dto.getId());
		service.update(hotel);
		if (hotel == new Hotel()) {

			log.error("There was a problem updting your Hotel (controller)");
			return "Hotel not updated";
		} else {

			log.info("Hotel updated (controller)");
			return "Hotel updated";
		}

	}

	/**
	 * read is a method which allows the user to get information about an existing
	 * Hotel object in the database.
	 * 
	 * @param id the parameter id is a Long attribute
	 * @return the return is a 'HotelDtoWithId' object
	 */
	@Override
	@GetMapping(path = "read/{id}")
	public HotelDtoWithId readById(@PathVariable(value = "id") Long id) {

		Hotel result = service.readById(id);
		if (result != null) {
			List<TravelDtoWithId> listTravel = new ArrayList<>();
			for (Travel travel : result.getLtravel()) {
				listTravel.add(new TravelDtoWithId(travel.getId(), travel.getNbrNight(), travel.getDestination(),
						travel.getPeriodBegin(), travel.getPeriodEnd(), null, null,
						new HotelDtoWithId(travel.getHotel().getId(), travel.getHotel().getName(),
								travel.getHotel().getDesc(), null, null)));
			}
			HotelDtoWithId dto = new HotelDtoWithId(result.getId(), result.getName(), result.getDesc(), listTravel,
					result.getStanding());
			if (dto == new HotelDtoWithId()) {
				log.error("There was an issue reading your Hotel (controller)");
				return null;
			} else {
				log.info("Your Hotel : (controller)");
				return dto;
			}

		} else {
			log.error("There was an issue reading your Hotel (controller)");
			return null;
		}
	}

	/**
	 * readall is a method which allows the user to get information about all the
	 * Hotel objects in the database.
	 * 
	 * @return the return is a list of 'HotelDtoWithId' representing the content of
	 *         the 'Hotel' objects in the database
	 */
	@Override
	@GetMapping(path = "readall")
	public List<HotelDtoWithId> readAll() {
		List<Hotel> result = service.readAll();
		List<HotelDtoWithId> listDto = new ArrayList<>();
		List<HotelDtoWithId> listEmpty = new ArrayList<>();
		for (Hotel temp : result) {
			List<TravelDtoWithId> listTravel = new ArrayList<>();
			for (Travel travel : temp.getLtravel()) {
				listTravel.add(new TravelDtoWithId(travel.getId(), travel.getNbrNight(), travel.getDestination(),
						travel.getPeriodBegin(), travel.getPeriodEnd(), null, null,
						new HotelDtoWithId(travel.getHotel().getId(), travel.getHotel().getName(),
								travel.getHotel().getDesc(), null, null)));
			}
			listDto.add(new HotelDtoWithId(temp.getId(), temp.getName(), temp.getDesc(), null, temp.getStanding()));
		}

		if (listDto.isEmpty()) {
			log.error("There was an issue reading all your Hotel (controller)");
			return listEmpty;
		} else {
			log.info("Your Hotel List: (controller)");
			return listDto;
		}

	}

	/**
	 * delete is a method which allows the user to delete an existing Hotel object
	 * in the database.
	 * 
	 * @param id is an Long attribute
	 * @return the return is a String describing the status of the method outcome
	 */
	@Override
	@DeleteMapping(path = "delete/{id}")
	public String delete(Long id) {

		if (service.deleteById(id)) {
			service.deleteById(id);
			log.info("Your Hotel was deleted (controller)");
			return "Hotel deleted";
		} else {
			log.error("There was an issue deleting your Hotel (controller)");
			return "Hotel NOT deleted";
		}
	}
}
