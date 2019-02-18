package com.fr.adaming.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IHotelDao;
import com.fr.adaming.entity.Hotel;

@Service
public class HotelService implements IService<Hotel>{

	@Autowired
	private IHotelDao dao;

	@Override
	public Hotel create(Hotel hotel) {
		if (hotel.getId() == null || hotel.getId() == 0L) {
			return dao.save(hotel);
		} else {
			return null;
		}
	}

	@Override
	public Hotel update(Hotel hotel) {
		if (hotel.getId() != null && hotel.getId() != 0L && dao.existsById(hotel.getId())) {
			return dao.save(hotel);
		} else {
			return null;
		}
	}

	@Override
	public Hotel readById(Long id) {
		return dao.findById(id);
	}
	
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public List<Hotel> readAll() {
		return dao.findAll();
	}
}