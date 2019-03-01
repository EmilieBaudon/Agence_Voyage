package com.fr.adaming.restcontroller;

import java.util.List;

import com.fr.adaming.dto.CustomerDto;
import com.fr.adaming.dto.CustomerDtoWithId;
import com.fr.adaming.dto.LoginDto;
import com.fr.adaming.dto.TechnicianDto;
import com.fr.adaming.dto.TechnicianDtoWithId;
import com.fr.adaming.entity.Person;

/**
 * 
 * @author EmilieBaudon
 *
 */
public interface IPersonController {

	/**
	 * Import data from user to the service Use the Create method to create a
	 * customer (for the technicians)
	 * 
	 * @param dto is a data transfer object representing the service
	 * @return a string saying if the creation has been successful
	 */
	public String create(CustomerDto dto);

	/**
	 * Import data from user to the service Use the Create method to register a
	 * customer
	 * 
	 * @param dto is a data transfer object representing the service
	 * @return a string saying if the registering has been successful
	 */
	public String register(CustomerDto dto);

	/**
	 * Import data from user to the service Use the Create method to create a
	 * technician (for the technicians)
	 * 
	 * @param dto is a data transfer object representing the service
	 * @return a string saying if the creation has been successful
	 */
	public String create(TechnicianDto dto);

	/**
	 * Import data from user to the service Use the Update method from person
	 * service exept email
	 * 
	 * @param dto is a data transfer object representing the service
	 * @return a string saying if the updating has been successful
	 */
	public String update(CustomerDtoWithId dto);

	/**
	 * Import data from user to the service Use the Update method from person
	 * service exept email
	 * 
	 * @param dto is a data transfer object representing the service
	 * @return a string saying if the updating has been successful
	 */
	public String update(TechnicianDtoWithId dto);

	/**
	 * Import data from user to the service Use the Update method from person
	 * service to update only email email
	 * 
	 * @param dto is a data transfer object representing the service
	 * @return a string saying if the updating email has been successful
	 */
	public String updateEmail(CustomerDtoWithId dto);

	/**
	 * Import data from user to the service Use the Update method from person
	 * service to update only email email
	 * 
	 * @param dto is a data transfer object representing the service
	 * @return a string saying if the updating email has been successful
	 */
	public String updateEmail(TechnicianDtoWithId dto);

	/**
	 * Import data from user to the service Use the read by email method from person
	 * service
	 * 
	 * @param email is a class attribute of Person
	 * @return Person is a class
	 */
	public Person readByEmail(String email);

	/**
	 * Import data from user to the service Use the read by id method from person
	 * service
	 * 
	 * @param id is the id of Person
	 * @return Person is a class
	 */
	public CustomerDtoWithId readByIdCustomer(Long id);

	public TechnicianDtoWithId readByIdTech(Long id);

	/**
	 * Import data from user to the service Use the read all method from person
	 * service
	 * 
	 * @return is a list of RegisterDto
	 */
	public List<CustomerDtoWithId> readAllCustomer();

	public List<TechnicianDtoWithId> readAllTech();

	/**
	 * Import data from user to the service Use the delete by id method from person
	 * service
	 * 
	 * @param id is the id of object that must be deleted
	 * @return a string saying if the delete has been successful
	 */
	public String deleteById(Long id);

	/**
	 * Import data from user to the service Use the delete by email method from
	 * person service
	 * 
	 * @param Email is a class attribute of Person
	 * @return a string saying if the delete by email has been successful
	 */
	public String deleteByEmail(String email);

	/**
	 * Import data from user to the service Use the delete by email method from
	 * person service
	 * 
	 * @param login contain the mail and the password
	 * @return a string saying if the login has been successful
	 */
	public String login(LoginDto login);
}
