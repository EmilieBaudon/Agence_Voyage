package com.fr.adaming.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.ITravelDao;
import com.fr.adaming.entity.Travel;

@Service
public class TravelService implements IService<Travel>{

	@Autowired
	private ITravelDao dao;

	@Override
	public Travel create(Travel travel) {
		if (travel.getId() == null || travel.getId() == 0L) {
			return dao.save(travel);
		} else {
			return null;
		}
	}

	@Override
	public Travel update(Travel travel) {
		if (travel.getId() != null && travel.getId() != 0L && dao.existsById(travel.getId())) {
			return dao.save(travel);
		} else {
			return null;
		}
	}

	@Override
	public Travel readById(Long id) {
		return dao.findById(id);
	}
	
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public List<Travel> readAll() {
		return dao.findAll();
	}
}
