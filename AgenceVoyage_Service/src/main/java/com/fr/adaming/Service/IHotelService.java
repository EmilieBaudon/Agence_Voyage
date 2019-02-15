package com.fr.adaming.Service;

import java.util.List;

import com.fr.adaming.entity.Hotel;

public interface IHotelService {

	public Hotel create (Hotel hotel);
	 public Hotel update (Hotel hotel);
	 public Hotel readById (Long id);
	 public List<Hotel> readAll ();
	
}
