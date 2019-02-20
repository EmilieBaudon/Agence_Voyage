package com.fr.adaming.Service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IHotelDao;
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
			Long id = standing.getHotel().getId();
			System.out.println(id);
			if (service.readById(id)!= null) {//test si l'hotel rentré existe
				log.info("Service created (service)");
				return dao.save(standing);
			}else {
				log.error("There was a problem creating your Service (service)");
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

		Standing test = dao.findById(id).get();

		if (test.equals(null)) {
			log.error("There was an issue reading your Service (service)");
			return null;
		} else {
			log.info("Your Service : (service)");
			return test;
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
			e.printStackTrace();
			log.error("There was an issue deleting your Service (service)");
			return false;
		}
	}

	/**
	 * read all the standings in the DB
	 * 
	 * @return list of all the standings in the database
	 */
	@Override
	public List<Standing> readAll() {
		List<Standing> listS = dao.findAll();

		if (listS.equals(null)) {
			log.error("There was an issue reading all your Services (service)");
			return null;
		} else {
			log.info("Your Service List: (service)");
			return listS;
		}
	}
}