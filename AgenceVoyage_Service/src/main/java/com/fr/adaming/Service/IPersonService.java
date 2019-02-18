package com.fr.adaming.Service;

import java.util.List;

import com.fr.adaming.entity.Person;

/**
 * 
 * @author EmilieBaudon
 *
 */
public interface IPersonService {

	/**
	 * @method Create the person given in the database Return Null if the person
	 *         already exist
	 */
	public Person create(Person person);

	/**
	 * @method Update the person given if the person do not exit in the database,
	 *         return Null
	 */
	public Person update(Person person);

	/**
	 * @method Return a person with its Mail Return Null if the Mail do not exist in
	 *         the database
	 */
	public Person readByEmail(String email);

	/**
	 * @method Return a person with its Mail Return Null if the Mail do not exist in
	 *         the database
	 */
	public Person readById(Long id);

	/**
	 * @method return all the people in the database return null if the database is
	 *         empty
	 */
	public List<Person> readAll();

	/**
	 * @method delete a person with its id if it exist in the database
	 */
	public boolean deleteById(Long id);

	/**
	 * @method delete a person with its Mail if it exist in the database
	 */
	public boolean deleteByEmail(String Email);

	/**
	 * @method Connect a person if its Mail and Pwd are correct Return null if it
	 *         does not exist
	 */
	public Person Login(String email, String pwd);
}
