package com.fr.adaming.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.ITravelDao;
import com.fr.adaming.entity.Travel;

/**
 * This class is the service part of the application for the travel model It
 * allows to make many interaction with the DB, create, update, read, delete an
 * travel and read all travels
 * 
 * @author Alan
 *
 */
@Service
public class TravelService implements IService<Travel> {

	/**
	 * @param Data access object of the travel
	 */
	@Autowired
	private ITravelDao dao;

	/**
	 * @method create an travel in the database the creation is done only if the id
	 *         of the object us null or equal to 0
	 */
	@Override
	public Travel create(Travel travel) {
		if (travel.getId() == null || travel.getId() == 0L) {
			return dao.save(travel);
		} else {
			return null;
		}
	}

	/**
	 * @method update an travel in the database the update is done only if the id of
	 *         the travel is found in the DB
	 */
	@Override
	public Travel update(Travel travel) {
		if (travel.getId() != null && travel.getId() != 0L && dao.existsById(travel.getId())) {
			return dao.save(travel);
		} else {
			return null;
		}
	}

	/**
	 * @method read an travel in the database thanks to the id put in the parameter
	 */
	@Override
	public Travel readById(Long id) {
		return dao.findById(id).get();
	}

	/**
	 * @method delete an travel in the database thanks to the id put in the
	 *         parameter
	 */
	public Boolean deleteById(Long id) {
		try {
			dao.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("DEBUG NON DELETE");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @method read all the travels in the DB
	 */
	@Override
	public List<Travel> readAll() {
		return dao.findAll();
	}
}
