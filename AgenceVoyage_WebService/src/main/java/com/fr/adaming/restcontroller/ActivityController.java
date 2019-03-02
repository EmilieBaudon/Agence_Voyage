package com.fr.adaming.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.ActivityDto;
import com.fr.adaming.entity.Activity;
import com.fr.adaming.service.ActivityService;

/**
 * 
 * @author victor The controller for all the flight objects. Allow to take in
 *         consideration requests from the web
 *
 */

@RestController
@RequestMapping(path = "activity/")
@CrossOrigin
public class ActivityController implements IController<ActivityDto, ActivityDto> {

	@Autowired
	private ActivityService serviceActivity;

	private Logger log = Logger.getLogger(ActivityService.class);

	/**
	 * import data from activity to the service Use the Update method from activity
	 * service
	 * 
	 * @param obj the Activity that will be created
	 * @return a String to signify if the method has worked
	 */

	@PostMapping(path = "create")
	public String createObject(@RequestBody ActivityDto obj) {
		Activity result = serviceActivity.create(new Activity(obj.getName(), obj.getDesc()));
		if (result != null) {
			log.info("activity created (controller)");
			return "activity created";
		} else {
			log.warn("The activity you want to create has an id which already exist (controller)");
			return "problem";
		}
	}

	/**
	 * import data from activity to the service Use the Update method from activity
	 * service
	 * 
	 * @param obj the activity to update
	 * @return a String to signify if the method has worked
	 */
	@PutMapping(path = "update")
	public String updateObject(@RequestBody ActivityDto obj) {
		Activity res = new Activity(obj.getName(), obj.getDesc());
		res.setId(obj.getId());
		Activity result = serviceActivity.update(res);
		if (result != null) {
			log.info("activity updated (controller)");
			return "activity updated";
		} else {
			log.warn("The activity you want to update has an id which already exist (controller)");
			return "problem";
		}
	}

	/**
	 * import data from activity to the service Use the read by id method from
	 * activity service
	 * 
	 * @param id of the activity
	 * @return the activity
	 */
	@GetMapping(path = "read/{id}")
	public ActivityDto readById(@PathVariable(value = "id") Long id) {
		Activity result = serviceActivity.readById(id);
		ActivityDto obj = new ActivityDto(result.getId(), result.getName(), result.getDesc());
		log.info("Activity print (controller)");
		return obj;
	}

	/**
	 * import data from activity to the service Use the read all method from
	 * activity service
	 * 
	 * @return a list with all the activities in the database
	 */
	@GetMapping(path = "readall")
	public List<ActivityDto> readAll() {
		List<Activity> result = serviceActivity.readAll();
		List<ActivityDto> listDto = new ArrayList<>();
		for (Activity temp : result) {
			listDto.add(new ActivityDto(temp.getId(), temp.getName(), temp.getDesc()));
		}
		log.info("List of activities printed (controller)");
		return listDto;
	}

	/**
	 * import data from activity to the service Use the delete by id method from
	 * activity service
	 * 
	 * @param id of the activity
	 * @return a String to signify if the method has worked
	 */
	@DeleteMapping(path = "delete/{id}")
	public String delete(@PathVariable(value = "id") Long id) {
		if (serviceActivity.deleteById(id)) {
			log.info("Activity deleted (controller)");
			return "the activity has been corectly delete";
		} else {
			log.error("Exception detected (controller)");
			return "the delete didn't work";
		}
	}

}
