package com.fr.adaming.Service;

import java.util.List;

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

	/**
	 * @method create an activity in the database
	 * the creation is done only if the id of the object us null or equal to 0
	 */
	@Override
	public Activity create(Activity activity) {
		if (activity.getId() == null || activity.getId() == 0L) {
			return dao.save(activity);
		} else {
			return null;
		}
	}

	/**
	 * @method update an activity in the database
	 * the update is done only if the id of the activity is found in the DB
	 */
	@Override
	public Activity update(Activity activity) {
		if (activity.getId() != null && activity.getId() != 0L && dao.existsById(activity.getId())) {
			return dao.save(activity);
		} else {
			return null;
		}
	}

	/**
	 * @method read an activity in the database thanks to the id put in the parameter
	 */
	@Override
	public Activity readById(Long id) {
		return dao.findById(id).get();
	}

	/**
	 * @method delete an activity in the database thanks to the id put in the parameter
	 */
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	/**
	 * @method read all the activities in the DB
	 */
	@Override
	public List<Activity> readAll() {
		return dao.findAll();
	}
}
