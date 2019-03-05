package com.fr.adaming.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import com.fr.adaming.dto.BookingDto;
import com.fr.adaming.dto.FlightDto;
import com.fr.adaming.dto.HotelDtoWithId;
import com.fr.adaming.dto.StandingDtoWithId;
import com.fr.adaming.dto.TravelDto;
import com.fr.adaming.dto.TravelDtoWithId;
import com.fr.adaming.entity.Booking;
import com.fr.adaming.entity.Flight;
import com.fr.adaming.entity.Hotel;
import com.fr.adaming.entity.Standing;
import com.fr.adaming.entity.Travel;
import com.fr.adaming.service.ActivityService;
import com.fr.adaming.service.TravelService;

/**
 * 
 * This Controller checks methods
 *
 *
 * @author Nicolas
 * 
 * 
 */

@RestController
@RequestMapping(path = "travel/")
@CrossOrigin
public class TravelController {

	/**
	 * @param service TravelService is an object used to access the database
	 */
	@Autowired
	private TravelService service;

	private Logger log = Logger.getLogger(ActivityService.class);

	/**
	 * CreateObject create an object in database with the parameter
	 * 
	 * @param dto is a data transfer object representing the service
	 * @return a string saying if the creation has been successful
	 */
	@PostMapping(path = "create")
	public String createObject(@Valid @RequestBody TravelDtoWithId dto) {
		Hotel hotel = new Hotel();
		hotel.setId(dto.getHotelDto().getId());
		
		List<Flight> listFlight = new ArrayList<>();

		for (FlightDto flight : dto.getLflightDto()) {
			listFlight.add(new Flight(flight.getIdPlane(), flight.getDateArrival(), flight.getDateDeparture(),
					flight.getAirportDeparture(), flight.getAirportArrival(), flight.getPrice()));
		}
		
		Travel travel = service.create(new Travel(dto.getNbrNight(), dto.getDestination(), dto.getPeriodBegin(),
				dto.getPeriodEnd(), null, listFlight, hotel));

		if (travel != null) {
			log.info("Travel created (controller)");
			return "Travel has been created";
		}

		else {
			log.warn("The travel you want to create has an id which already exist (controller)");
			return "Travel has not been created";

		}
	}

	/**
	 * UpdateObject update an object in database
	 * 
	 * @param dto is a data transfer object representing the service
	 * @return a string saying if the updating has been successful
	 */
	@PutMapping(path = "update")
	public String updateObject(@Valid @RequestBody TravelDtoWithId dto) {
		Standing standing = new Standing(dto.getHotelDto().getLstandingDto().getNbRoom(), dto.getHotelDto().getLstandingDto().getPriceChild(), dto.getHotelDto().getLstandingDto().getPriceAdult(), dto.getHotelDto().getLstandingDto().getDesc(), dto.getHotelDto().getLstandingDto().getLactivity());
		standing.setId(dto.getHotelDto().getLstandingDto().getId());
		
		Hotel hotel = new Hotel(dto.getHotelDto().getName(), dto.getHotelDto().getDesc(), null,standing);
		hotel.setId(dto.getHotelDto().getId());
		
		List<Flight> listFlight = new ArrayList<>();

		for (FlightDto flight : dto.getLflightDto()) {
			listFlight.add(new Flight(flight.getIdPlane(), flight.getDateArrival(), flight.getDateDeparture(),
					flight.getAirportDeparture(), flight.getAirportArrival(), flight.getPrice()));
		}
		
		Travel travel = new Travel(dto.getNbrNight(), dto.getDestination(), dto.getPeriodBegin(), dto.getPeriodEnd(),
				null, listFlight, hotel);
		travel.setId(dto.getId());
		service.update(travel);
		if (travel == new Travel()) {
			log.warn("The travel you want to update has an id which already exist (controller)");
			return "Travel has not been updated";
		}

		else {
			log.info("Travel updated (controller)");
			return "Travel has been updated";
		}
	}

	/**
	 * ReadById read by id an object in database
	 * 
	 * @param id is the id of TravelDtoWithId
	 * @return a dto object
	 */
	@GetMapping(path = "read/{id}")
	public TravelDtoWithId readById(@PathVariable(value = "id") Long id) {

		Travel result = service.readById(id);
		List<BookingDto> listBooking = new ArrayList<>();
		List<FlightDto> listFlight = new ArrayList<>();
		
		for (Booking booking : result.getLbooking()) {
			listBooking.add(new BookingDto(booking.getNbrAdult(), booking.getNbrChild(), booking.getTotalPrice(),
					booking.getPointAddFidelity(), booking.getCustomer().getId(), booking.getTravel().getId()));
		}
		for (Flight flight : result.getLflight()) {
			listFlight.add(new FlightDto(flight.getIdPlane(), flight.getDateArrival(), flight.getDateDeparture(),
					flight.getAirportDeparture(), flight.getAirportArrival(), flight.getPrice()));
		}
		TravelDtoWithId dto = new TravelDtoWithId(result.getId(), result.getNbrNight(), result.getDestination(),
				result.getPeriodBegin(), result.getPeriodEnd(), listBooking, listFlight,
				new HotelDtoWithId(result.getHotel().getId(), result.getHotel().getName(), result.getHotel().getDesc(),
						null, result.getHotel().getStanding()));
		log.info("Travel print (controller)");
		return dto;
	}

	/**
	 * ReadAll read all travels in database
	 * 
	 * @return an array list of TravelDtoWithId
	 */
	@GetMapping(path = "readall")
	public List<TravelDtoWithId> readAll() {
		List<Travel> result = service.readAll();
		List<TravelDtoWithId> listDto = new ArrayList<>();
		for (Travel temp : result) {
			List<BookingDto> listBooking = new ArrayList<>();
			List<FlightDto> listFlight = new ArrayList<>();
			for (Booking booking : temp.getLbooking()) {
				listBooking.add(new BookingDto(booking.getNbrAdult(), booking.getNbrChild(), booking.getTotalPrice(),
						booking.getPointAddFidelity(), booking.getCustomer().getId(), booking.getTravel().getId()));
			}
			for (Flight flight : temp.getLflight()) {
				listFlight.add(new FlightDto(flight.getIdPlane(), flight.getDateArrival(), flight.getDateDeparture(),
						flight.getAirportDeparture(), flight.getAirportArrival(), flight.getPrice()));
			}
			listDto.add(new TravelDtoWithId(temp.getId(), temp.getNbrNight(), temp.getDestination(),
					temp.getPeriodBegin(), temp.getPeriodEnd(), listBooking, listFlight,
					new HotelDtoWithId(temp.getHotel().getId(), temp.getHotel().getName(), temp.getHotel().getDesc(),
							null, temp.getHotel().getStanding())));
		}
		log.info("List of travels printed (controller)");
		return listDto;

	}

	/**
	 * Delete delete an object with the id
	 * 
	 * @param id of the object that must be deleted
	 * @return a string saying if the delete has been successful
	 */
	@DeleteMapping(path = "delete/{id}")
	public String delete(Long id) {
		if (service.deleteById(id)) {
			log.info("Travel deleted (controller)");
			return "Travel has been deleted";
		} else {
			log.error("Exception detected (controller)");
			return "Can't delete ! ";
		}
	}
}
