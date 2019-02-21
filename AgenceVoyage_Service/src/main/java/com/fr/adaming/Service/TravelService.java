package com.fr.adaming.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
	 * create an travel in the database the creation is done only if the id of the
	 * object us null or equal to 0
	 * 
	 * @param travel to create
	 * @return the travel created
	 */
	@Override
	public Travel create(Travel travel) {
		if ((travel.getId() == null || travel.getId() == 0L)
				&& (travel.getPeriodBegin() != null && travel.getPeriodBegin().isAfter(LocalDate.now()))) {
			travel.setPeriodEnd(travel.getPeriodBegin().plusDays(travel.getNbrNight() + 1L));
			log.info("activity created (service)");
			return dao.save(travel);
		} else {
			log.warn("The activity you want to create has an id which already exist (service)");
			return null;
		}
	}

	/**
	 * update an travel in the database the update is done only if the id of the
	 * travel is found in the DB
	 * 
	 * @param travel to update
	 * @return travel updated
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
	 * read an travel in the database thanks to the id put in the parameter
	 * 
	 * @param id of the travel
	 * @return the travel
	 */
	@Override
	public Travel readById(Long id) {
		Travel travel = null;
		Optional<Travel> optValue = dao.findById(id);
		try {
			if (optValue.isPresent()) {
				travel = optValue.get();
			}
			log.info("read by id done in service");
			return travel;
		} catch (Exception e) {
			log.error("This id does not exist", e);
			return null;
		}

	}

	/**
	 * delete an travel in the database thanks to the id put in the parameter
	 * 
	 * @param id of the travel
	 * @return boolean true if the method has success and false if it has not
	 */
	public Boolean deleteById(Long id) {
		try {
			dao.deleteById(id);
			log.info("Travel deleted (service)");
			return true;
		} catch (Exception e) {
			log.error("Exception detected (service)", e);
			return false;
		}
	}

	/**
	 * read all the travels in the DB
	 * 
	 * @return list of all the travels in the database
	 */
	@Override
	public List<Travel> readAll() {
		log.info("List of activities printed (service)");
		return dao.findAll();
	}
}
