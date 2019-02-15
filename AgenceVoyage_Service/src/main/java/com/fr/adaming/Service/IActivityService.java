package com.fr.adaming.Service;

import java.util.List;

import com.fr.adaming.entity.Activity;

public interface IActivityService {

	public Activity create (Activity activity);
	 public Activity update (Activity activity);
	 public Activity readById (Long id);
	 public List<Activity> readAll ();
	
}
