package com.fr.adaming.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IFlightDao;
import com.fr.adaming.entity.Flight;

@Service
public class FlightService implements IService<Flight>{

	@Autowired
	private IFlightDao dao;

	@Override
	public Flight create(Flight flight) {
		if (flight.getId() == null || flight.getId() == 0L) {
			return dao.save(flight);
		} else {
			return null;
		}
	}

	@Override
	public Flight update(Flight flight) {
		if (flight.getId() != null && flight.getId() != 0L && dao.existsById(flight.getId())) {
			return dao.save(flight);
		} else {
			return null;
		}
	}

	@Override
	public Flight readById(Long id) {
		return dao.findById(id);
	}
	
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public List<Flight> readAll() {
		return dao.findAll();
	}
}