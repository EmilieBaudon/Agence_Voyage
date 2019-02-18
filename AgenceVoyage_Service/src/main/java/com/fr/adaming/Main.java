package com.fr.adaming;

import com.fr.adaming.Service.ActivityService;
import com.fr.adaming.entity.Activity;

public class Main {

	public static void main(String[] args) {
		
	ActivityService service = new ActivityService();
	Activity act = new Activity("coucou", "tu veux voir ma bite?");
		
		service.create(act);
	}

}
