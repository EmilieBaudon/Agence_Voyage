package com.fr.adaming.restcontroller;

import java.util.List;


/**
 * 
 * @author Alan
 *
 * @param <T> represent the classes service as Activity, Booking...
 * 
 */
public interface IController<T,U> {

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
	public String updateObject(U obj);

	/**
	 * 
	 * @param id is a Long representing the id  of the service
	 * @return a string saying if the creation has been successfull
	 */
	public U readById(Long id);

	/**
	 * 
	 * @return the list of all the object of a table of the DB
	 */
	public List<U> readAll();

	/**
	 * 
	 * @param id is a Long representing the id  of the service
	 * @return a string saying if the delete has been successfull
	 */
	public String delete(Long id);
}