package com.fr.adaming.Service;

import java.util.List;

public interface ITravelService {

	public Travel create (Travel travel);
	 public Travel update (Travel travel);
	 public Travel readById (Long id);
	 public List<Travel> readAll ();
	
}
