package com.fr.adaming.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.dao.IPersonDao;
import com.fr.adaming.entity.Person;

public class PersonService implements IPersonService {
	
	@Autowired
	IPersonDao dao;

	@Override
	public Person create(Person person) {
		if ((person.getId() == null || !dao.existsById(person.getId())) && person.getMail() != null) {
			if (dao.findByMail(person.getMail()) == null){			
				return dao.save(person);
			}else{
				return null;
			}
		}else {
			return null;
		}
	}

	@Override
	public Person update(Person person) {
		if (person.getId() != null && dao.existsById(person.getId())) {
			if (dao.findByMail(person.getMail()) == null){			
				return dao.save(person);
			}else{
				return null;
			}
		}else {
			return null;
		}
	}

	@Override
	public Person readByEmail(String email) {
		if (email != null) {
			try {
				dao.findByMail(email);
				return dao.findByMail(email);
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
		
	}

	@Override
	public Person readById(Long id) {
		if (id != null) {
			return dao.findById(id).get();
		}else {
			return null;
		}
	}

	@Override
	public List<Person> readAll() {
		if (!dao.findAll().isEmpty()) {
			return dao.findAll();
		} else {
			return null;
		}
	}

	@Override
	public void deleteById(Long id) {
				dao.deleteById(id);
	}

	@Override
	public void deleteByEmail(String email) {
		Person user = dao.findByMail(email);
		dao.deleteById(user.getId());
	}

	@Override
	public Person Login(String email, String pwd) {
		Person login=null;
		try {
			login = dao.findByMailAndPwd(email, pwd);
			return login;
		} catch (Exception e) {
			return login;
		}
	}
}
