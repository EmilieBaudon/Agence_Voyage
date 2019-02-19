package com.fr.adaming.Service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IStandingDao;
import com.fr.adaming.entity.Standing;

/**
 * This class is the service part of the application for the standing model It
 * allows to make many interaction with the DB, create, update, read, delete an
 * standing and read all standings
 * 
 * @author Alan
 *
 */
@Service
public class StandingService implements IService<Standing> {
	
	private Logger log = Logger.getLogger(ActivityService.class);

	/**
	 * @param Data access object of the standing
	 */
	@Autowired
	private IStandingDao dao;

	/**
	 * @method create an standing in the database the creation is done only if the
	 *         id of the object us null or equal to 0
	 */
	public Standing create(Standing standing) {
		if (standing.getId() == null || standing.getId() == 0L) {
			return dao.save(standing);
		} else {
			return null;
		}
	}

	/**
	 * @method update an standing in the database the update is done only if the id
	 *         of the standing is found in the DB
	 */
	@Override
	public Standing update(Standing standing) {
		if (standing.getId() != null && standing.getId() != 0L && dao.existsById(standing.getId())) {
			return dao.save(standing);
		} else {
			return null;
		}
	}

	/**
	 * @method read an standing in the database thanks to the id put in the
	 *         parameter
	 */
	@Override
	public Standing readById(Long id) {
		return dao.findById(id).get();
	}

	/**
	 * @method delete an standing in the database thanks to the id put in the
	 *         parameter
	 */
	public Boolean deleteById(Long id) {
		try {
			dao.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @method read all the standings in the DB
	 */
	@Override
	public List<Standing> readAll() {
		return dao.findAll();
	}
}