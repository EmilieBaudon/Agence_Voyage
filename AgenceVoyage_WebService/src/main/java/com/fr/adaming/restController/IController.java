package com.fr.adaming.restController;

import java.util.List;


/**
 * 
 * @author Alan
 *
 * @param <T> represent the classes service as Activity, Booking...
 * 
 */
public interface IController<T> {

	/**
	 * 
	 * @param obj is an object representing the service
	 * @return a string saying if the creation has been successfull
	 */
	public String createObject(T obj);

	/**
	 * 
	 * @param obj is an object representing the service
	 * @return a string saying if the creation has been successfull
	 */
	public String updateObject(T obj);

	/**
	 * 
	 * @param id is a Long representing the id  of the service
	 * @return a string saying if the creation has been successfull
	 */
	public T readById(Long id);

	/**
	 * 
	 * @return the list of all the object of a table of the DB
	 */
	public List<T> readAll();

	/**
	 * 
	 * @param id is a Long representing the id  of the service
	 * @return a string saying if the delete has been successfull
	 */
	public String delete(Long id);
}