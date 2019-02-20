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
	 * Create the person given in the database Return Null if the person already
	 * exist
	 * 
	 * @param dto the person to be registered
	 * @return the string of response
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
	 * Create the person given in the database Return Null if the person already
	 * exist
	 * 
	 * @param dto the person to be updated
	 * @return the string of response
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
	 * Create the person given in the database Return Null if the person already
	 * exist
	 * 
	 * @param dto the person to be created
	 * @return the string of response
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
	 * Update the person given if the person do not exit in the database, return
	 * Null
	 * 
	 * @param dto the person to be updated
	 * @return the string of response
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
	 * Update the person given if the person do not exit in the database, return
	 * Null
	 * 
	 * @param dto the person to be updated
	 * @return the string of response
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
	 * Update the mail of a person
	 * 
	 * @param dto the person to be updated
	 * @return the string of response
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
	 * Update the mail of a technician
	 * 
	 * @param dto the person to be updated
	 * @return the string of response
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
	 * Read an object person with his email
	 * 
	 * @param email of the person
	 * @return the person to be read from DB with email
	 */
	@Override
	@RequestMapping(path = "read/{email}", method = RequestMethod.GET)
	public Person readByEmail(String email) {
		return service.readByEmail(email);
	}

	/**
	 * Read an object person with his id
	 * 
	 * @param id of the person
	 * @return the person to be read from DB with email
	 */
	@Override
	@RequestMapping(path = "read/{id}", method = RequestMethod.GET)
	public Person readById(Long id) {
		return service.readById(id);
	}

	/**
	 * Read all persons in DB
	 * 
	 * @return the person to be read from DB with email
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
	 * Delete a person in the DB using his id
	 * 
	 * @param id of the person
	 * @return return a string of validation
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
	 * Delete a person in the DB using his email
	 * 
	 * @param email of the person
	 * @return return a string of validation
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
	 * method for a log
	 * 
	 * @param login of the person wich is a combination of a mail and a password
	 * @return return a string of validation
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
