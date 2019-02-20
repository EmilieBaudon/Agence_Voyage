package com.fr.adaming.Service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IActivityDao;
import com.fr.adaming.entity.Activity;

/**
 * This class is the service part of the application for the activity model It
 * allows to make many interaction with the DB, create, update, read, delete an
 * activity and read all activities
 * 
 * @author Alan
 *
 */
@Service
public class ActivityService implements IService<Activity> {

	/**
	 * @param Data access object of the activity
	 */
	@Autowired
	private IActivityDao dao;

	private Logger log = Logger.getLogger(ActivityService.class);

	/**
	 * This method create an activity in the database the creation is done only if
	 * the id of the object us null or equal to 0
	 * 
	 * @return an object activity
	 * 
	 * @param activity an object activity
	 */
	@Override
	public Activity create(Activity activity) {
		if (activity.getId() == null || activity.getId() == 0L) {
			log.info("activity created (service)");
			return dao.save(activity);
		} else {
			log.warn("The activity you want to create has an id which already exist (service)");
			return null;
		}
	}

	/**
	 * This method update an activity in the database the update is done only if the id
	 *         of the activity is found in the DB
	 *         
	 * @return an object activity
	 * 
	 * @param activity an object activity      
	 */
	@Override
	public Activity update(Activity activity) {
		if (activity.getId() != null && activity.getId() != 0L && dao.existsById(activity.getId())) {
			log.info("activity updated (service)");
			return dao.save(activity);
		} else {
			log.warn("The activity you want to update has an id which already exist (service)");
			return null;
		}
	}

	/**
	 * This method read an activity in the database thanks to the id put in the
	 *         parameter
	 *@return an object activity
	 * 
	 * @param id an id representing the activity id    
	 */
	@Override
	public Activity readById(Long id) {
		log.info("Activity print (service)");
		return dao.findById(id).get();
	}

	/**
	 * This method delete an activity in the database thanks to the id put in the
	 * parameter
	 * 
	 * @return a boolean, true if the delete happened and false otherwise
	 * 
	 * @param id an id representing the activity id
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
	 * This method read all the activities in the DB
	 * 
	 *@return a list of activity from the database
	 */
	@Override
	public List<Activity> readAll() {
		log.info("List of activities printed (service)");
		return dao.findAll();
	}
}
