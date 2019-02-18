package com.fr.adaming.restController;
//javadoc
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.Service.ActivityService;
import com.fr.adaming.entity.Activity;

@RestController
@RequestMapping(path="activity/")
public class ActivityController implements IController<Activity> {
	
	@Autowired	
	private ActivityService serviceActivity;	
	
	@RequestMapping(path="create",method=RequestMethod.POST)
	public String createObject(@RequestBody Activity societe) {
		Activity result=serviceActivity.create(societe);	
		if(result!=null) {
			return "société créer";
		}else {
			return"problem";
		}
	}
	
	@RequestMapping(path="update",method=RequestMethod.POST)
	public String updateObject(@RequestBody Activity societe) {
		Activity result=serviceActivity.update(societe);	
		if(result!=null) {
			return "société modifier";
		}else {
			return"problem";
		}
	}
	
	@RequestMapping(path="read/{id}",method=RequestMethod.GET)
	public Activity readById(@PathVariable(value="id") Long id) {
		return serviceActivity.readById(id);	
	}
	
	@RequestMapping(path="readall",method=RequestMethod.GET)
	public List<Activity> readAll() {
		return serviceActivity.readAll();		
	}
	
	@RequestMapping(path="delete/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable(value="id") Long id) {		
			if(serviceActivity.deleteById(id)==true) {
				return"the activity has been corectly delete";
			}else {
				return"the delete didn't work";
			}
	}

	

	

	

}
