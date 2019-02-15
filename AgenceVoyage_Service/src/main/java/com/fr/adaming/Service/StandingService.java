package com.fr.adaming.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StandingService implements IService<Standing>{

	@Autowired
	private IStandingDao dao;

	@Override
	public Standing create(Standing standing) {
		if (standing.getId() == null || standing.getId() == 0L) {
			return dao.save(standing);
		} else {
			return null;
		}
	}

	@Override
	public Standing update(Standing standing) {
		if (standing.getId() != null && standing.getId() != 0L && dao.existsById(standing.getId())) {
			return dao.save(standing);
		} else {
			return null;
		}
	}

	@Override
	public Standing readById(Long id) {
		return dao.findByID(id);
	}
	
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public List<Standing> readAll() {
		return dao.findAll();
	}
}