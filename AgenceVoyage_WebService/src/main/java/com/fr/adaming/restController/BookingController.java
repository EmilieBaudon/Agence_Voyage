package com.fr.adaming.restController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.Service.IService;
import com.fr.adaming.dto.BookingDtoWithId;
import com.fr.adaming.dto.CustomerDtoWithId;
import com.fr.adaming.dto.TravelDtoWithId;
import com.fr.adaming.entity.Booking;
import com.fr.adaming.entity.Customer;
import com.fr.adaming.entity.Travel;

/**
 * 
 * @author Karguel(Mathieu)
 * 
 * This class is the controller of Bookings which controlls the requests and the data flows
 *
 */

@RestController
@RequestMapping(path = "/booking")
public class BookingController {
/**
 * 
 * 
 */
	@Autowired
	@Qualifier("BookingService")
	IService<Booking> service;

	@RequestMapping(path = "create", method = RequestMethod.POST)
	public String create(@Valid @RequestBody BookingDtoWithId dto) {
//		Customer cust = new Customer(dto.getCustomerDto().getCard(),dto.getCustomerDto().getFidelityPoint(), dto.getCustomerDto().getLbookingDto());
		Customer cust = new Customer();
		cust.setId(dto.getCustomerDto().getId());

//		Travel travel = new Travel(obj.getTravelDto().getNbrNight(),obj.getTravelDto().getDestination(), obj.getTravelDto().getPeriodBegin(),obj.getTravelDto().getPeriodEnd(), null, null, null);
		Travel travel = new Travel();
		travel.setId(dto.getTravelDto().getId());
		Booking result = service.create(new Booking(dto.getNbrAdult(), dto.getNbrChild(), dto.getTotalPrice(),
				dto.getPointAddFidelity(), cust, travel));
		if (result != null) {
			return "Booking created YEAH !";
		} else {
			return "Booking not created ...";
		}
	}

	@RequestMapping(path = "update", method = RequestMethod.PUT)
	public String update(@Valid @RequestBody BookingDtoWithId dto) {

		Customer cust = new Customer();
		cust.setId(dto.getCustomerDto().getId());

		Travel travel = new Travel();
		travel.setId(dto.getTravelDto().getId());
		Booking result = service.update(new Booking(dto.getNbrAdult(), dto.getNbrChild(), dto.getTotalPrice(),
				dto.getPointAddFidelity(), cust, travel));

		if (result != null) {
			return "Booking updated YEAH !";
		} else {
			return "Booking not updated ...";
		}

	}

	@RequestMapping(path = "read/{id}", method = RequestMethod.GET)
	public BookingDtoWithId readById(Long id) {
		Booking result = service.readById(id);
		CustomerDtoWithId custDtoId = new CustomerDtoWithId();
		custDtoId.setId(result.getCustomer().getId());
		TravelDtoWithId travelDtoId = new TravelDtoWithId();
		travelDtoId.setId(result.getTravel().getId());
		BookingDtoWithId dto = new BookingDtoWithId(result.getId(), result.getNbrAdult(), result.getNbrChild(),
				result.getTotalPrice(), result.getPointAddFidelity(), custDtoId, travelDtoId);
		return dto;
	}

	@RequestMapping(path = "readall", method = RequestMethod.GET)
	public List<BookingDtoWithId> readAll() {
		List<Booking> lresult = service.readAll();
		List<BookingDtoWithId> lresultDto = new ArrayList<BookingDtoWithId>();
		for (Booking j : lresult) {
			CustomerDtoWithId custDtoId = new CustomerDtoWithId();
			custDtoId.setId(j.getCustomer().getId());
			TravelDtoWithId travelDtoId = new TravelDtoWithId();
			travelDtoId.setId(j.getTravel().getId());
			BookingDtoWithId dto = new BookingDtoWithId(j.getId(), j.getNbrAdult(), j.getNbrChild(), j.getTotalPrice(),
					j.getPointAddFidelity(), custDtoId, travelDtoId);
			lresultDto.add(dto);
		}
		return  lresultDto;
	}

	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public String delete(Long id) {
		service.deleteById(id);
		return "A booking has been delete";
	}

}
