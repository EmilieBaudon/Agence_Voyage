package com.fr.adaming.Service;

import java.util.List;


/**
 * 
 * @author Alan
 *
 * @param <T> represent the classes service as Activity, Booking...
 * 
 */
public interface IService<T> {
	/**
	 * 
	 * @param activity is an object representing the service
	 * @return returns an object T that is return from the database at the creation of the object
	 */
	public T create(T object);
	/**
	 * 
	 * @param activity is an object representing the service
	 * @return returns an object T that is return from the database at the update of the object
	 */
	public T update(T object);

	/**
	 * 
	 * @param id is a Long representing the id  of the service
	 * @return returns an object T that is return from the database using the parameter id
	 */
	public T readById(Long id);

	/**
	 * 
	 * @return returns all the objects T  in a list of T that is return from the database 
	 */
	public List<T> readAll();

	/**
	 * 
	 * @param id is a Long representing the id  of the service
	 * @return return True if the delete haas worked and return false if it hasn't 
	 */
	public Boolean deleteById(Long id);
}
