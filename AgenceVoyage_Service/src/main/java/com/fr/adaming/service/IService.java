package com.fr.adaming.service;

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
	 * This method create an object T in the database
	 * @param object is an object representing the service
	 * @return returns an object T that is return from the database at the creation of the object
	 */
	public T create(T object);
	/**
	 * This method update an object T in the database
	 * @param object is an object representing the service
	 * @return returns an object T that is return from the database at the update of the object
	 */
	public T update(T object);

	/**
	 * This method read an object T in the database with his id
	 * @param id is a Long representing the id  of the service
	 * @return returns an object T that is return from the database using the parameter id
	 */
	public T readById(Long id);

	/**
	 * This method read all the T object in the database
	 * @return returns all the objects T  in a list of T that is return from the database 
	 */
	public List<T> readAll();

	/**
	 * This method delete the objects thanks to it's id
	 * @param id is a Long representing the id  of the service
	 * @return return True if the delete has worked and return false if it hasn't 
	 */
	public Boolean deleteById(Long id);
}
