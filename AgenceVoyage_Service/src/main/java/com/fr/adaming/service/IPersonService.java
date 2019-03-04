package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Customer;
import com.fr.adaming.entity.Person;
import com.fr.adaming.entity.Technician;

/**
 * 
 * @author EmilieBaudon
 *
 */
public interface IPersonService {

	/**
	 * Create the person given in the database Return Null if the person already
	 * exist
	 * 
	 * @param person the person to be created
	 * @return the person created
	 */
	public Person create(Person person);

	/**
	 * Update the person given if the person do not exit in the database, return
	 * Null
	 * 
	 * @param person the person to be updated
	 * @return the person updated
	 */
	public Person update(Person person);

	/**
	 * This method Return a person with its Mail Return Null if the Mail do not
	 * exist in the database
	 * 
	 * @param email a string with an email value of the person
	 * @return the person read
	 */
	//public Person readByEmail(String email);
	public Customer readByEmail(String email);

	/**
	 * This method Return a person with its Mail Return Null if the Mail do not
	 * exist in the database
	 * 
	 * @param id a long id representing the id of the person to be read
	 * @return the person read
	 */
	public Customer readById(Long id);
	public Technician readByIdTech(Long id);

	/**
	 * This method return all the people in the database return null if the database
	 * is empty
	 * 
	 * @return the list of person in the database
	 */
	public List<Customer> readAllCustomer();
	public List<Technician> readAllTech();

	/**
	 * This method delete a person with its id if it exist in the database
	 * 
	 * @param id a long id representing the id of the person to be delete
	 * @return a boolean, true of the delete has happened and false otherwise
	 */
	public boolean deleteById(Long id);

	/**
	 * This method delete a person with its Mail if it exist in the database
	 * 
	 * @param Email a string with an email value of the person
	 * @return a boolean, true of the delete has happened and false otherwise
	 */
	public boolean deleteByEmail(String email);

	/**
	 * This method Connect a person if its Mail and Pwd are correct Return null if
	 * it does not exist
	 * 
	 * @param email a string with an email value of the person
	 * @param pwd a string with a password
	 * @return the person read
	 */
	public Person login(String email, String pwd);
}
