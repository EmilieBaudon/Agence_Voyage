package com.fr.adaming.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.BookingDto;
import com.fr.adaming.dto.BookingDtoWithId;
import com.fr.adaming.dto.CustomerDtoWithId;
import com.fr.adaming.dto.TravelDtoWithId;
import com.fr.adaming.entity.Booking;
import com.fr.adaming.entity.Customer;
import com.fr.adaming.entity.Travel;
import com.fr.adaming.service.IService;
import com.fr.adaming.service.TravelService;

/**
 * 
 * @author Karguel(Mathieu)
 * 
 *         This class is the controller of Bookings which controls the web
 *         requests and the data flows
 *
 */

@RestController
@RequestMapping(path = "/booking")
public class BookingController {

	@Autowired
	@Qualifier("BookingService")
	IService<Booking> service;
	private Logger log = Logger.getLogger(BookingController.class);

	@Autowired
	private TravelService trS;
	
	private static final String RETURNNULL = "return from service is null";

	/**
	 * 
	 * @param BookingdtoWithId is the argument of the method which create an object
	 *                         Booking in the data base with the following
	 *                         parameters
	 * @return an advice about success is returned
	 */

	@PostMapping(path = "create")
	public String create(@Valid @RequestBody BookingDto dto) {
		Customer cust = new Customer();
		cust.setId(dto.getIdcustomerDto());

		Travel travel = new Travel();
		travel.setId(dto.getIdtravelDto());

		Booking result = new Booking(dto.getNbrAdult(), dto.getNbrChild(), dto.getTotalPrice(),
				dto.getPointAddFidelity(), cust, travel);

		result = service.create(result);

		if (result != null) {
			log.info("booking created in controller");
			return "Booking created YEAH !";
		} else {
			log.warn(RETURNNULL);
			return "Booking not created ...";
		}
	}

	/**
	 * 
	 * @param BookingDtoWithId dto, StandingDtoWithId Stdto, FlightDtoWithId Fdto
	 *                         are the arguments of data transfert from the database
	 * @return createPlus is a method which create a booking with the totalPrice
	 *         calculated and set
	 */

	@PostMapping(path = "createPlus")
	public String createPlus(@Valid @RequestBody BookingDto dto) {
		Customer cust = new Customer();
		cust.setId(dto.getIdcustomerDto());

		Travel travel = trS.readById(dto.getIdtravelDto());

		Double priceF1 = 0d;
		Double priceF2 = 0d;
		Double priceA = 0d;
		Double priceC = 0d;

		try {
			if (travel.getLflight().size() >= 2) {
				priceF1 = travel.getLflight().get(0).getPrice();
				priceF2 = travel.getLflight().get(1).getPrice();
			} else {
				log.warn("It needs 2 flights in the LFlight list of the travel (BookingController)");
			}
		} catch (Exception e) {
			log.warn("It needs 2 flights in the LFlight list of the travel (BookingController)");
		}
		try {
			priceA = travel.getHotel().getStanding().getPriceAdult();
			priceC = travel.getHotel().getStanding().getPriceChild();

		} catch (Exception e) {
			log.warn("No price in the standing Booking (controller)");
		}

		Double totalPrice = (priceF1 + priceF2) * (dto.getNbrAdult() + dto.getNbrChild()) + (priceA * dto.getNbrAdult())
				+ (priceC * dto.getNbrChild());

		Booking result = new Booking(dto.getNbrAdult(), dto.getNbrChild(), totalPrice, dto.getPointAddFidelity(), cust,
				travel);

		result = service.create(result);

		if (result != null) {
			log.info("booking created in controller");
			return "Booking updated YEAH !";
		} else {
			log.warn(RETURNNULL);
			return "Booking not updated ...";
		}
	}

	/**
	 * 
	 * @param BookingDtoWithId dto is the argument of data transfert from the
	 *                         database
	 * @return a message which validate or not the updating
	 * 
	 */
	@PutMapping(path = "update")
	public String update(@Valid @RequestBody BookingDtoWithId dto) {

		Customer cust = new Customer();
		cust.setId(dto.getCustomerDto().getId());

		Travel travel = new Travel();
		travel.setId(dto.getTravelDto().getId());
		Booking result = service.update(new Booking(dto.getNbrAdult(), dto.getNbrChild(), dto.getTotalPrice(),
				dto.getPointAddFidelity(), cust, travel));

		if (result != null) {
			log.info("booking updated in controller");
			return "Booking updated YEAH !";
		} else {
			log.warn(RETURNNULL);
			return "Booking not updated ...";
		}

	}

	/**
	 * 
	 * @param Use the parameter Long id to find and get a booking from the database
	 * @return the booking asked
	 */
	@GetMapping(path = "read/{id}")
	public BookingDtoWithId readById(Long id) {
		Booking result = service.readById(id);
		CustomerDtoWithId custDtoId = new CustomerDtoWithId();
		custDtoId.setId(result.getCustomer().getId());
		TravelDtoWithId travelDtoId = new TravelDtoWithId();
		travelDtoId.setId(result.getTravel().getId());
		BookingDtoWithId dto = new BookingDtoWithId(result.getId(), result.getNbrAdult(), result.getNbrChild(),
				result.getTotalPrice(), result.getPointAddFidelity(), custDtoId, travelDtoId);

		if (dto == new BookingDtoWithId()) {
			log.error("An error occurs getting the booking");
			return null;
		} else {
			log.info("THE booking");
			return dto;
		}
	}

	/**
	 * 
	 * @param allows to get the list of the booking
	 * @return a list of booking
	 */
	@GetMapping(path = "readall")
	public List<BookingDtoWithId> readAll() {
		List<Booking> lresult = service.readAll();
		List<BookingDtoWithId> lresultDto = new ArrayList<>();
		for (Booking j : lresult) {
			CustomerDtoWithId custDtoId = new CustomerDtoWithId();
			custDtoId.setId(j.getCustomer().getId());
			TravelDtoWithId travelDtoId = new TravelDtoWithId();
			travelDtoId.setId(j.getTravel().getId());
			BookingDtoWithId dto = new BookingDtoWithId(j.getId(), j.getNbrAdult(), j.getNbrChild(), j.getTotalPrice(),
					j.getPointAddFidelity(), custDtoId, travelDtoId);
			lresultDto.add(dto);
		}

		if (lresultDto.isEmpty()) {
			log.error("An error occurs getting the list of bookings");
			return lresultDto;
		} else {
			log.info("The list of Bookings");
			return lresultDto;
		}

	}

	/**
	 * 
	 * @param use the id to delete a booking from the database
	 * @return VOID
	 */
	@DeleteMapping(path = "delete/{id}")
	public String delete(Long id) {
		if (service.deleteById(id)) {
			log.info("Booking deleted (controller)");
			return "Booking deleted";
		} else {
			log.error("An error occurs Booking not deleted (controller)");
			return "Booking NOT deleted";
		}

	}

}
