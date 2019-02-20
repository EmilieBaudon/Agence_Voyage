package com.fr.adaming.Service;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
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

	private Logger log = Logger.getLogger(TravelService.class);

	/**
	 * @method create an travel in the database the creation is done only if the id
	 *         of the object us null or equal to 0
	 */
	@Override
	public Travel create(Travel travel) {
		if ((travel.getId() == null || travel.getId() == 0L) && (travel.getPeriodBegin() != null &&travel.getPeriodBegin().isAfter(LocalDate.now()))) {
			travel.setPeriodEnd(travel.getPeriodBegin().plusDays(travel.getNbrNight() + 1));
			travel.setPeriodBegin(travel.getPeriodBegin().plusDays(1));
			log.info("activity created (service)");
			return dao.save(travel);
		} else {
			log.warn("The activity you want to create has an id which already exist (service)");
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
			log.info("activity updated (service)");
			return dao.save(travel);
		} else {
			log.warn("The activity you want to update has an id which already exist (service)");
			return null;
		}
	}

	/**
	 * @method read an travel in the database thanks to the id put in the parameter
	 */
	@Override
	public Travel readById(Long id) {
		log.info("Activity print (service)");
		return dao.findById(id).get();
	}

	/**
	 * @method delete an travel in the database thanks to the id put in the
	 *         parameter
	 */
	public Boolean deleteById(Long id) {
		try {
			dao.deleteById(id);
			log.info("Activity deleted (service)");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception detected (service)");
			return false;
		}
	}

	/**
	 * @method read all the travels in the DB
	 */
	@Override
	public List<Travel> readAll() {
		log.info("List of activities printed (service)");
		return dao.findAll();
	}
}
