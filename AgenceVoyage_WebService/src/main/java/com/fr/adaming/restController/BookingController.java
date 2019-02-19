package com.fr.adaming.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.Service.IService;
import com.fr.adaming.dto.BookingDtoWithId;
import com.fr.adaming.entity.Booking;
import com.fr.adaming.entity.Customer;
import com.fr.adaming.entity.Travel;

@RestController
@RequestMapping(path = "/booking")
public class BookingController {

	@Autowired
	@Qualifier("BookingService")
	IService<Booking> service;

	@RequestMapping(path = "create", method = RequestMethod.POST)
	public String create(@RequestBody BookingDtoWithId dto) {
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
			return "Booking pas créé ...";
		}
	}

}
