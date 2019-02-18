package com.fr.adaming.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.Service.ActivityService;
import com.fr.adaming.entity.Activity;

@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * 
 * @author victo
 *
 */
public class ActivityServiceTest {
	@Autowired
	private ActivityService activityService;

	@Test
	public void createActivityTest() {		
		 /** 
		  *  test the insertion of an activity which already exist in database
		  */
		Activity activity = new Activity("activity", "test");
		Activity result = activityService.create(activity);
		assertNotNull(result);

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
		
		Activity activity = new Activity("adaming", "test");
		Activity result = activityService.create(activity);
		activity.setName("activityUpdate");
		activity = activityService.update(activity);
		assertNotNull(activity);
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteActivityTest() {
		/** 
		 * test the delete of an activity which doesn't exist
		 */
		
		Activity activity = new Activity();
		activity.setId(99999L);
		activityService.deleteById(activity.getId());

	}

}
