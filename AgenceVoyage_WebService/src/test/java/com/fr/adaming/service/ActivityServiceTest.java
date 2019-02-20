package com.fr.adaming.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import com.fr.adaming.Service.ActivityService;
import com.fr.adaming.Service.FlightService;
import com.fr.adaming.entity.Activity;
import com.fr.adaming.entity.Flight;
import com.fr.adaming.entity.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * 
 * @author victor
 *
 */
public class ActivityServiceTest {
	@Autowired
	private ActivityService activityService;

	private Activity activity;
	
	@Test
	public void createActivityTest() {		
		 /** 
		  *  test the insertion of an activity which already exist in database
		  */
		Activity activity = new Activity("activity", "test");
		activity  = activityService.create(activity);
		assertNotNull(activity);

	}

	@Test
	public void updateActivityTest() {
		 
		/** 
		 * test the modification of an activity which doesn't exist in database
		 */
		
		Activity activity = new Activity();
		activity.setId(99999L);
		activity = activityService.update(activity);
		assertNull(activity);

	}

	@Test
	public void updateActivityTest2() {
		/** 
		 * test the modification of an activity which already exist
		 */
		Activity activity = new Activity("activity", "test");
		activity  = activityService.create(activity);
		activity = activityService.readAll().get(0);		
		assertNotNull(activityService.update(activity));
	}
	
	@Test
	public void deleteActivityTest() {
		/** 
		 * test the delete of an activity which doesn't exist
		 */
		Boolean objDelete = activityService.deleteById(9999999L);
		assertFalse(objDelete);
	}	

	@Test(expected = NoSuchElementException.class)
	public void readNonExistingObject() {
		/** 
		 * test the read of an activity which doesn't exist
		 */
		activity = activityService.readById(9999999L);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void readAllEmptyTable() {
		/** 
		 * test the read of an list of activity which is empty
		 */
		activity = activityService.readAll().get(0);
	}

	@Test
	public void readExistingObject() {
		/** 
		 * test the read of an activity which exist
		 */
		Activity activity = new Activity("adaming", "test");
		Activity result = activityService.create(activity);
		activity = activityService.readAll().get(0);
		activity = activityService.readById(activity.getId());
		assertNotNull(activity);
	}

	@After
	public void suppr() {
		List <Activity> activities = activityService.readAll();
		if (activities != null) {
			for (int i=0; i<activities.size();i++) {
				activityService.deleteById(activities.get(i).getId());
			}
		}
	}

}
