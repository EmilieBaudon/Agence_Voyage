package com.fr.adaming.restController;
import java.util.ArrayList;
//javadoc
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.Service.ActivityService;
import com.fr.adaming.dto.ActivityDto;
import com.fr.adaming.dto.FlightDto;
import com.fr.adaming.entity.Activity;
import com.fr.adaming.entity.Flight;
/**
 * 
 * @author victo
 * The controller for all the flight objects. Allow to take in consideration requests from the web
 *
 */
@RestController
@RequestMapping(path="activity/")
public class ActivityController implements IController<ActivityDto,ActivityDto> {
	
	@Autowired	
	private ActivityService serviceActivity;	
	/**
	 * @method import data from activity to the service
	 * Use the Update method from activity service
	 */
	@RequestMapping(path="create",method=RequestMethod.POST)
	public String createObject(@RequestBody ActivityDto obj) {
		Activity result=serviceActivity.create(new Activity(obj.getName(),obj.getDesc()));	
		if(result!=null) {
			return "activity created";
		}else {
			return"problem";
		}
	}
	/**
	 * @method import data from activity to the service
	 * Use the Update method from activity service
	 */
	@RequestMapping(path="update",method=RequestMethod.POST)
	public String updateObject(@RequestBody ActivityDto obj) {
		Activity res = new Activity(obj.getName(), obj.getDesc());
		res.setId(obj.getId());		
		Activity result=serviceActivity.update(res);	
		if(result!=null) {
			return "activity updated";
		}else {
			return"problem";
		}
	}
	/**
	 * @method import data from activity to the service
	 * Use the read by id method from activity service
	 */
	@RequestMapping(path="read/{id}",method=RequestMethod.GET)
	public ActivityDto readById(@PathVariable(value="id") Long id) {
		Activity result= serviceActivity.readById(id);	
		ActivityDto obj = new ActivityDto(result.getId(),result.getName(),result.getDesc());
		return obj;
	}
	/**
	 * @method import data from activity to the service
	 * Use the read all method from activity service
	 */
	@RequestMapping(path="readall",method=RequestMethod.GET)
	public List<ActivityDto> readAll() {
		List<Activity> result= serviceActivity.readAll();
		List<ActivityDto> listDto=new ArrayList<ActivityDto>();
		for (Activity temp : result) {
			listDto.add(new ActivityDto(temp.getId(),temp.getName(),temp.getDesc()));
		}
		return listDto;
	}
	/**
	 * @method import data from activity to the service
	 * Use the delete by id method from activity service
	 */
	@RequestMapping(path="delete/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable(value="id") Long id) {		
			if(serviceActivity.deleteById(id)==true) {
				return"the activity has been corectly delete";
			}else {
				return"the delete didn't work";
			}
	}

}
