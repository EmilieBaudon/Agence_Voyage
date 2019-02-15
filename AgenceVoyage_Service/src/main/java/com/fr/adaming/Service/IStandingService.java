package com.fr.adaming.Service;

import java.util.List;

import com.fr.adaming.entity.Standing;

public interface IStandingService {

	public Standing create (Standing standing);
	 public Standing update (Standing standing);
	 public Standing readById (Long id);
	 public List<Standing> readAll ();
	
}
