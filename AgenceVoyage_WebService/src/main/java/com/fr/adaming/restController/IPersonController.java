package com.fr.adaming.restController;

import java.util.List;

import com.fr.adaming.dto.CustomerDto;
import com.fr.adaming.dto.CustomerDtoWithId;
import com.fr.adaming.dto.LoginDto;
import com.fr.adaming.dto.RegisterDto;
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
	 * @method import data from user to the service Use the Create method to create
	 *         a customer (for the technicians)
	 */
	public String create(CustomerDto dto);
	
	/**
	 * @method import data from user to the service Use the Create method to
	 *         register a customer
	 */
	public String register(CustomerDto dto);
	
	/**
	 * @method import data from user to the service Use the Create method to create
	 *         a technician (for the technicians)
	 */
	public String create(TechnicianDto dto);
	
	/**
	 * @method import data from user to the service Use the Update method from
	 *         person service exept email
	 */
	public String update(CustomerDtoWithId dto);
	
	/**
	 * @method import data from user to the service Use the Update method from
	 *         person service exept email
	 */
	public String update(TechnicianDtoWithId dto);
	
	/**
	 * @method import data from user to the service Use the Update method from
	 *         person service to update only email email
	 */
	public String updateEmail(CustomerDtoWithId dto);
	
	/**
	 * @method import data from user to the service Use the Update method from
	 *         person service to update only email email
	 */
	public String updateEmail(TechnicianDtoWithId dto);
	
	/**
	 * @method import data from user to the service Use the read by email method
	 *         from person service
	 */
	public Person readByEmail(String email);
	
	/**
	 * @method import data from user to the service Use the read by id method from
	 *         person service
	 */
	public Person readById(Long id);
	
	/**
	 * @method import data from user to the service Use the read all method from
	 *         person service
	 */
	public List<RegisterDto> readAll();
	
	/**
	 * @method import data from user to the service Use the delete by id method from
	 *         person service
	 */
	public String deleteById(Long id);
	
	/**
	 * @method import data from user to the service Use the delete by email method
	 *         from person service
	 */
	public String deleteByEmail(String Email);
	
	/**
	 * @method import data from user to the service Use the delete by email method
	 *         from person service
	 */
	public String Login(LoginDto login);
}
