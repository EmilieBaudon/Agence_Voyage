package com.fr.adaming.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IBookingDao;
import com.fr.adaming.entity.Booking;

@Service
public class BookingService implements IService<Booking>{

	@Autowired
	private IBookingDao dao;

	@Override
	public Booking create(Booking booking) {
		if (booking.getId() == null || booking.getId() == 0L) {
			return dao.save(booking);
		} else {
			return null;
		}
	}

	@Override
	public Booking update(Booking booking) {
		if (booking.getId() != null && booking.getId() != 0L && dao.existsById(booking.getId())) {
			return dao.save(booking);
		} else {
			return null;
		}
	}

	@Override
	public Booking readById(Long id) {
		return dao.findById(id);
	}
	
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public List<Booking> readAll() {
		return dao.findAll();
	}
}
