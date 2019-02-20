package com.fr.adaming.restController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.Service.IPersonService;
import com.fr.adaming.Service.PersonService;
import com.fr.adaming.dto.CustomerDto;
import com.fr.adaming.dto.CustomerDtoWithId;
import com.fr.adaming.dto.LoginDto;
import com.fr.adaming.dto.RegisterDto;
import com.fr.adaming.dto.TechnicianDto;
import com.fr.adaming.dto.TechnicianDtoWithId;
import com.fr.adaming.entity.Customer;
import com.fr.adaming.entity.Person;
import com.fr.adaming.entity.Technician;

/**
 * 
 * @author EmilieBaudon Controller for Customers
 *
 */
@RestController
public class PersonController implements IPersonController {

	@Autowired
	@Qualifier("PersonService")
	IPersonService service;
	private Logger log = Logger.getLogger(PersonController.class);	

	/**
	 * @method import data from user to the service Use the Create method to
	 *         register a customer
	 */

	@RequestMapping(path = "register", method = RequestMethod.POST)
	public String register(@Valid @RequestBody CustomerDto dto) {
		Customer person = new Customer(dto.getName(), dto.getFirstName(), dto.getBirthDate(), dto.getAdress(),
				dto.getMail(), dto.getPwd(), dto.getCard(), null, null);
		Person result = service.create(person);
		if (result != null) {
			log.info("person register in controller");
			return "person has been created";
		} else {
			log.warn("return from service is null");
			return "person could not be created";
		}
	}

	/**
	 * @method import data from user to the service Use the Create method to create
	 *         a customer (for the technicians)
	 */

	@RequestMapping(path = "createCustomer", method = RequestMethod.POST)
	public String create(@Valid @RequestBody CustomerDto dto) {
		Customer person = new Customer(dto.getName(), dto.getFirstName(), dto.getBirthDate(), dto.getAdress(),
				dto.getMail(), dto.getPwd(), dto.getCard(), null, null);
		Person result = service.create(person);
		if (result != null) {
			log.info("customer created in controller");
			return "person has been created";
		} else {
			log.warn("return from service is null");
			return "person could not be created";
		}
	}

	/**
	 * @method import data from user to the service Use the Create method to create
	 *         a technician (for the technicians)
	 */
	@Override
	@RequestMapping(path = "createTech", method = RequestMethod.POST)
	public String create(@Valid @RequestBody TechnicianDto dto) {
		Technician person = new Technician(dto.getName(), dto.getFirstName(), dto.getBirthDate(), dto.getAdress(),
				dto.getMail(), dto.getPwd(), dto.getJob(), dto.getJobStartDate());
		Person result = service.create(person);
		if (result != null) {
			log.info("technician created in controller");
			return "person has been created";
		} else {
			log.warn("return from service is null");
			return "person could not be created";
		}
	}

	/**
	 * @method import data from user to the service Use the Update method from
	 *         person service exept email
	 */
	@Override
	@RequestMapping(path = "updateCustomer", method = RequestMethod.PUT)
	public String update(@Valid @RequestBody CustomerDtoWithId dto) {
		Customer person = new Customer(dto.getName(), dto.getFirstName(), dto.getBirthDate(), dto.getAdress(),
				service.readById(dto.getId()).getMail(), dto.getPwd(), dto.getCard(), null, null);
		person.setId(dto.getId());
		Person result = service.update(person);
		if (result != null) {
			log.info("customer updated in controller");
			return "person has been updated";
		} else {
			log.warn("return from service is null");
			return "person could not be updated";
		}
	}

	/**
	 * @method import data from user to the service Use the Update method from
	 *         person service exept email
	 */
	@Override
	@RequestMapping(path = "updateTech", method = RequestMethod.PUT)
	public String update(@Valid @RequestBody TechnicianDtoWithId dto) {
		Technician person = new Technician(dto.getName(), dto.getFirstName(), dto.getBirthDate(), dto.getAdress(),
				service.readById(dto.getId()).getMail(), dto.getPwd(), dto.getJob(), dto.getJobStartDate());
		person.setId(dto.getId());
		Person result = service.update(person);
		if (result != null) {
			log.info("technician updated in controller");
			return "person has been updated";
		} else {
			log.warn("return from service is null");
			return "person could not be updated";
		}
	}
	
	/**
	 * @method import data from user to the service Use the Update method from
	 *         person service to update only email email
	 */
	@Override
	@RequestMapping(path = "updateCustomerEmail", method = RequestMethod.PUT)
	public String updateEmail(@Valid @RequestBody CustomerDtoWithId dto) {
		Customer person = (Customer) service.readById(dto.getId());
		person.setId(dto.getId());
		person.setMail(dto.getMail());
		Person result = service.update(person);
		if (result != null) {
			log.info("customer updated in controller");
			return "person has been updated";
		} else {
			log.warn("return from service is null");
			return "person could not be updated";
		}
	}

	/**
	 * @method import data from user to the service Use the Update method from
	 *         person service to update only email email
	 */
	@Override
	@RequestMapping(path = "updateTechEmail", method = RequestMethod.PUT)
	public String updateEmail(@Valid @RequestBody TechnicianDtoWithId dto) {
		Technician person = (Technician) service.readById(dto.getId());
		person.setId(dto.getId());
		person.setMail(dto.getMail());
		Person result = service.update(person);
		if (result != null) {
			return "person has been updated";
		} else {
			log.warn("return from service is null");
			return "person could not be updated";
		}
	}

	/**
	 * @method import data from user to the service Use the read by email method
	 *         from person service
	 */
	@Override
	@RequestMapping(path = "read/{email}", method = RequestMethod.GET)
	public Person readByEmail(String email) {
		return service.readByEmail(email);
	}

	/**
	 * @method import data from user to the service Use the read by id method from
	 *         person service
	 */
	@Override
	@RequestMapping(path = "read/{id}", method = RequestMethod.GET)
	public Person readById(Long id) {
		return service.readById(id);
	}

	/**
	 * @method import data from user to the service Use the read all method from
	 *         person service
	 */
	@Override
	@RequestMapping(path = "readAll", method = RequestMethod.GET)
	public List<RegisterDto> readAll() {
		List<Person> people = service.readAll();
		List<RegisterDto> dtos = new ArrayList<>();
		for (int i = 0; i < people.size(); i++) {
			dtos.add(
					new RegisterDto(people.get(i).getName(), people.get(i).getFirstName(), people.get(i).getBirthDate(),
							people.get(i).getAdress(), people.get(i).getMail(), people.get(i).getPwd()));
		}
		log.info("list complete for readAll in controller");
		return dtos;
	}

	/**
	 * @method import data from user to the service Use the delete by id method from
	 *         person service
	 */
	@Override
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public String deleteById(Long id) {
		if (service.deleteById(id)) {
			service.deleteById(id);
			log.info("person has been deleted in controller");
			return "Person has been deleted";
		} else {
			log.warn("person has not been deleted in controller");
			return "Person could not be deleted";
		}
	}

	/**
	 * @method import data from user to the service Use the delete by email method
	 *         from person service
	 */
	@Override
	@RequestMapping(path = "delete/{email}", method = RequestMethod.DELETE)
	public String deleteByEmail(String email) {
		if (service.deleteByEmail(email)) {
			service.deleteByEmail(email);
			log.info("person has been deleted in controller");
			return "Person has been deleted";
		} else {
			log.warn("person has not been deleted in controller");
			return "Person could not be deleted";
		}
	}

	/**
	 * @method import data from user to the service Use the delete by email method
	 *         from person service
	 */
	@Override
	@RequestMapping(path = "login", method = RequestMethod.POST)
	public String Login(@RequestBody LoginDto login) {
		Person result = service.Login(login.getMail(), login.getPwd());
		if (result != null) {
			log.info("connection done in controller");
			return "Login done";
		} else {
			log.warn("connection can not be done in controller");
			return "Email or password are incorrect";
		}
	}
}
