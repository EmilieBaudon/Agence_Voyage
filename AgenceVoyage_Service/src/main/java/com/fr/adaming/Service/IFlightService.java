package com.fr.adaming.Service;

import java.util.List;

public interface IFlightService {

	public Flight create (Flight flight);
	 public Flight update (Flight flight);
	 public Flight readById (Long id);
	 public List<Flight> readAll ();
	
}
