package com.fr.adaming.Service;

import java.util.List;

public interface IBookingService {

	public Booking create (Booking booking);
	 public Booking update (Booking booking);
	 public Booking readById (Long id);
	 public List<Booking> readAll ();
	
}
