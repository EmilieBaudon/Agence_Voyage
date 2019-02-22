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
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.CustomerDto;
import com.fr.adaming.dto.CustomerDtoWithId;
import com.fr.adaming.dto.LoginDto;
import com.fr.adaming.dto.RegisterDto;
import com.fr.adaming.dto.TechnicianDto;
import com.fr.adaming.dto.TechnicianDtoWithId;
import com.fr.adaming.entity.Customer;
import com.fr.adaming.entity.Person;
import com.fr.adaming.entity.Technician;
import com.fr.adaming.service.IPersonService;

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
	private static final String RESPONSE = "person has been created";
	private static final String NULLRESPONSE = "return from service is null";
	private static final String UPDATERESPONSE = "person has been updated";
	private static final String NOTCREATED = "person could not be created";
	private static final String NOTUPDATED = "person could not be updated";

	/**
	 * Create the person given in the database Return Null if the person already
	 * exist
	 * 
	 * @param dto the person to be registered
	 * @return the string of response
	 */
	@PostMapping(path = "register")
	public String register(@Valid @RequestBody CustomerDto dto) {
		Customer person = new Customer(dto.getName(), dto.getFirstName(), dto.getBirthDate(), dto.getAdress(),
				dto.getMail(), dto.getPwd(), dto.getCard());
		Person result = service.create(person);
		if (result != null) {
			log.info("person register in controller");
			return RESPONSE;
		} else {
			log.warn(NULLRESPONSE);
			return NOTCREATED;
		}
	}

	/**
	 * Create the person given in the database Return Null if the person already
	 * exist
	 * 
	 * @param dto the person to be updated
	 * @return the string of response
	 */

	@PostMapping(path = "createCustomer")
	public String create(@Valid @RequestBody CustomerDto dto) {
		Customer person = new Customer(dto.getName(), dto.getFirstName(), dto.getBirthDate(), dto.getAdress(),
				dto.getMail(), dto.getPwd(), dto.getCard());
		Person result = service.create(person);
		if (result != null) {
			log.info("customer created in controller");
			return RESPONSE;
		} else {
			log.warn(NULLRESPONSE);
			return NOTCREATED;
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
	@PutMapping(path = "updateCustomer")
	public String update(@Valid @RequestBody CustomerDtoWithId dto) {
		Customer person = new Customer(dto.getName(), dto.getFirstName(), dto.getBirthDate(), dto.getAdress(),
				service.readById(dto.getId()).getMail(), dto.getPwd(), dto.getCard());
		person.setId(dto.getId());
		Person result = service.update(person);
		if (result != null) {
			log.info("customer updated in controller");
			return UPDATERESPONSE;
		} else {
			log.warn(NULLRESPONSE);
			return NOTUPDATED;
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
	@PutMapping(path = "updateTech")
	public String update(@Valid @RequestBody TechnicianDtoWithId dto) {
		Technician person = new Technician(dto.getName(), dto.getFirstName(), dto.getBirthDate(), dto.getAdress(),
				service.readById(dto.getId()).getMail(), dto.getPwd(), dto.getJob(), dto.getJobStartDate());
		person.setId(dto.getId());
		Person result = service.update(person);
		if (result != null) {
			log.info("technician updated in controller");
			return UPDATERESPONSE;
		} else {
			log.warn(NULLRESPONSE);
			return NOTUPDATED;
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
	@PostMapping(path = "createTech")
	public String create(@Valid @RequestBody TechnicianDto dto) {
		Technician person = new Technician(dto.getName(), dto.getFirstName(), dto.getBirthDate(), dto.getAdress(),
				dto.getMail(), dto.getPwd(), dto.getJob(), dto.getJobStartDate());
		Person result = service.create(person);
		if (result != null) {
			log.info("technician created in controller");
			return RESPONSE;
		} else {
			log.warn(NULLRESPONSE);
			return NOTCREATED;
		}
	}

	/**
	 * Update the mail of a person
	 * 
	 * @param dto the person to be updated
	 * @return the string of response
	 */
	@Override
	@PutMapping(path = "updateCustomerEmail")
	public String updateEmail(@Valid @RequestBody CustomerDtoWithId dto) {
		Customer person = (Customer) service.readById(dto.getId());
		person.setId(dto.getId());
		person.setMail(dto.getMail());
		Person result = service.update(person);
		if (result != null) {
			log.info("customer updated in controller");
			return UPDATERESPONSE;
		} else {
			log.warn(NULLRESPONSE);
			return NOTUPDATED;
		}
	}

	/**
	 * Update the mail of a technician
	 * 
	 * @param dto the person to be updated
	 * @return the string of response
	 */
	@Override
	@PutMapping(path = "updateTechEmail")
	public String updateEmail(@Valid @RequestBody TechnicianDtoWithId dto) {
		Technician person = (Technician) service.readById(dto.getId());
		person.setId(dto.getId());
		person.setMail(dto.getMail());
		Person result = service.update(person);
		if (result != null) {
			return UPDATERESPONSE;
		} else {
			log.warn(NULLRESPONSE);
			return NOTUPDATED;
		}
	}

	/**
	 * Read an object person with his email
	 * 
	 * @param email of the person
	 * @return the person to be read from DB with email
	 */
	@Override
	@GetMapping(path = "read/{email}")
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
	@GetMapping(path = "read/{id}")
	public Person readById(Long id) {
		return service.readById(id);
	}

	/**
	 * Read all persons in DB
	 * 
	 * @return the person to be read from DB with email
	 */
	@Override
	@GetMapping(path = "readAll")
	public List<RegisterDto> readAll() {
		List<Person> people = service.readAll();
		List<RegisterDto> dtos = new ArrayList<>();
		for (int i = 0; i < people.size(); i++) {
			dtos.add(
					new RegisterDto(people.get(i).getName(), people.get(i).getBirthDate(), people.get(i).getFirstName(),
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
	@DeleteMapping(path = "delete/{id}")
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
	@DeleteMapping(path = "delete/{email}")
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
	@PostMapping(path = "login")
	public String login(@RequestBody LoginDto login) {
		Person result = service.login(login.getMail(), login.getPwd());
		if (result != null) {
			log.info("connection done in controller");
			return "Login done";
		} else {
			log.warn("connection can not be done in controller");
			return "Email or password are incorrect";
		}
	}
}
