package com.fr.adaming.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IPersonDao;
import com.fr.adaming.entity.Person;

/**
 * 
 * @author EmilieBaudon
 *
 */
@Service
@Qualifier("PersonService")
public class PersonService implements IPersonService {

	/**
	 * @param dao contain all the CRUD methods (Create, Read, Update and delete)
	 */
	@Autowired
	IPersonDao dao;

	/**
	 * @method Create the person given in the database Return Null if the person
	 *         already exist
	 */
	@Override
	public Person create(Person person) {
		if ((person.getId() == null || person.getId() == 0L || !dao.existsById(person.getId())) && person.getMail() != null) {
			if (dao.findByMail(person.getMail()) == null) {
				return dao.save(person);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * @method Update the person given if the person do not exit in the database,
	 *         return Null
	 */
	@Override
	public Person update(Person person) {
		if (person.getId() != null && dao.existsById(person.getId())) {
			if (dao.findByMail(person.getMail()) == null) {
				return dao.save(person);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * @method Return a person with its Mail Return Null if the Mail do not exist in
	 *         the database
	 */
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

	/**
	 * @method Return a person with its Mail Return Null if the Mail do not exist in
	 *         the database
	 */
	@Override
	public Person readById(Long id) {
		try {
			Person person = dao.findById(id).get();
			return person;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @method return all the people in the database return null if the database is
	 *         empty
	 */
	@Override
	public List<Person> readAll() {
		if (!dao.findAll().isEmpty()) {
			return dao.findAll();
		} else {
			return null;
		}
	}

	/**
	 * @method delete a person with its id if it exist in the database
	 */
	@Override
	public boolean deleteById(Long id) {
		try {
			dao.deleteById(id);
			return true;
		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @method delete a person with its Mail if it exist in the database
	 */
	@Override
	public boolean deleteByEmail(String email) {
		try {
			Person user = dao.findByMail(email);
			dao.deleteById(user.getId());
			return true;
		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @method Connect a person if its Mail and Pwd are correct Return null if it
	 *         does not exist
	 */
	@Override
	public Person Login(String email, String pwd) {
		Person login = null;
		try {
			login = dao.findByMailAndPwd(email, pwd);
			return login;
		} catch (Exception e) {
			return login;
		}
	}
}
