package com.fr.adaming.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	 * @param service from hotel
	 */
	@Autowired
	private HotelService service;

	/**
	 * create an standing in the database the creation is done only if the id of the
	 * object us null or equal to 0
	 * 
	 * @param standing to create
	 * @return the standing created
	 */
	public Standing create(Standing standing) {
		if (standing.getId() == null || standing.getId() == 0L) {

			if (service.readById(standing.getHotel().getId()) != null) {// test si l'hotel rentré existe

				log.info("Standing created (service)");
				return dao.save(standing);
			} else {
				log.error("There was a problem creating your Standing (service)");
				return null;
			}
		} else {
			log.error("There was a problem creating your Service (service)");
			return null;
		}
	}

	/**
	 * update an standing in the database the update is done only if the id of the
	 * standing is found in the DB
	 * 
	 * @param standing to update
	 * @return the standing updated
	 */
	@Override
	public Standing update(Standing standing) {
		if (standing.getId() != null && standing.getId() != 0L && dao.existsById(standing.getId())) {
			log.info("Service updated (service)");
			return dao.save(standing);
		} else {
			log.error("There was a problem updting your Service (service)");
			return null;
		}
	}

	/**
	 * read an standing in the database thanks to the id put in the parameter
	 * 
	 * @param id of the standing
	 * @return standing
	 */
	@Override
	public Standing readById(Long id) {
		Standing standing = null;
		Optional<Standing> optValue = dao.findById(id);
		try {
			if (optValue.isPresent()) {
				standing = optValue.get();
			}
			log.info("read by id done in service");
			return standing;
		} catch (Exception e) {
			log.error("This id does not exist", e);
			return null;
		}

	}

	/**
	 * read all the standings in the DB
	 * 
	 * @return list of all the standings in the database
	 */
	@Override
	public List<Standing> readAll() {
		List<Standing> list = dao.findAll();
		List<Standing> listEmpty = new ArrayList<>();
		if (!list.isEmpty()) {
			log.info("read all done in service");
			return list;
		} else {
			log.warn("database is empty");
			return listEmpty;
		}
	}

	/**
	 * delete an standing in the database thanks to the id put in the parameter
	 * 
	 * @param id of the standing
	 * @return boolean true if the method worked and false if not
	 */
	public Boolean deleteById(Long id) {
		try {
			dao.deleteById(id);
			log.info("Your Service was deleted (service)");
			return true;
		} catch (Exception e) {

			log.error("There was an issue deleting your Service (service)", e);
			return false;
		}
	}
}