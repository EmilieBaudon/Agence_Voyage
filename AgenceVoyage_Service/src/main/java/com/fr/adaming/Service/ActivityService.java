package com.fr.adaming.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IActivityDao;
import com.fr.adaming.entity.Activity;

@Service
public class ActivityService implements IService<Activity>{

	@Autowired
	private IActivityDao dao;

	@Override
	public Activity create(Activity activity) {
		if (activity.getId() == null || activity.getId() == 0L) {
			return dao.save(activity);
		} else {
			return null;
		}
	}

	@Override
	public Activity update(Activity activity) {
		if (activity.getId() != null && activity.getId() != 0L && dao.existsById(activity.getId())) {
			return dao.save(activity);
		} else {
			return null;
		}
	}

	@Override
	public Activity readById(Long id) {
		return dao.findById(id);
	}
	
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public List<Activity> readAll() {
		return dao.findAll();
	}
}
