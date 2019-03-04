package com.fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.ICustomerDao;
import com.fr.adaming.dao.IPersonDao;
import com.fr.adaming.dao.ITechDao;
import com.fr.adaming.entity.Customer;
import com.fr.adaming.entity.Person;
import com.fr.adaming.entity.Technician;

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
	@Autowired
	ICustomerDao daoc;
	@Autowired
	ITechDao daot;
	private Logger log = Logger.getLogger(PersonService.class);

	/**
	 * This method Create the person given in the database Return Null if the person
	 * already exist
	 * 
	 * @param person an object person to be created
	 * 
	 * @return the object person created
	 */
	@Override
	public Person create(Person person) {
		if ((person.getId() == null || person.getId() == 0L || !dao.existsById(person.getId()))
				&& person.getMail() != null) {
			if (dao.findByMail(person.getMail()) == null) {
				log.info("Person created in service");
				return dao.save(person);
			} else {
				log.warn("Mail already exist");
				return null;
			}
		} else {
			log.warn("Id already exist or email is null");
			return null;
		}
	}

	/**
	 * This method Update the person given if the person do not exit in the
	 * database, return Null
	 * 
	 * @param person an object person to be updated
	 * 
	 * @return the object person updated
	 */
	@Override
	public Person update(Person person) {
		if (person.getId() != null && dao.existsById(person.getId())) {
			log.info("Person updated in service");
			return dao.save(person);
		} else {
			log.info("Id is null or doesn't exist");
			return null;
		}
	}

	/**
	 * This method Return a person with its Mail Return Null if the Mail do not
	 * exist in the database
	 * 
	 * @param email a String representing the person email
	 * 
	 * @return the object read
	 */
//	@Override
//	public Person readByEmail(String email) {
//		if (email != null) {
//			try {
//				dao.findByMail(email);
//				log.info("person read in service");
//				return dao.findByMail(email);
//			} catch (Exception e) {
//				log.error("this email does not exist");
//				return null;
//			}
//		} else {
//			log.warn("email can not be null");
//			return null;
//		}
//	}
	@Override
	public Customer readByEmail(String email) {
		if (email != null) {
			try {
				dao.findByMail(email);
				log.info("person read in service");
				return daoc.findByMail(email);
			} catch (Exception e) {
				log.error("this email does not exist");
				return null;
			}
		} else {
			log.warn("email can not be null");
			return null;
		}
	}

	/**
	 * This method Return a person with its Mail Return Null if the Mail do not
	 * exist in the database
	 * 
	 * @param id a Long representing the person id
	 * 
	 * @return the object read
	 */
	@Override
	public Customer readById(Long id) {
		try {
			Customer customer = daoc.findById(id).get();
			log.info("read by id done in service");
			return customer;
		} catch (Exception e) {
			log.error("This id does not exist");
			return null;
		}
	}
	
	@Override
	public Technician readByIdTech(Long id) {
		try {
			Technician tech = daot.findById(id).get();
			log.info("read by id done in service");
			return tech;
		} catch (Exception e) {
			log.error("This id does not exist");
			return null;
		}
	}

	/**
	 * This method return all the people in the database return null if the database
	 * is empty
	 * 
	 * @return a list of Person from the DB
	 */
	@Override
	public List<Customer> readAllCustomer() {
		List<Customer> listEmpty = new ArrayList<>();
		if (!dao.findAll().isEmpty()) {
			log.info("read all done in service");
			return daoc.findAll();
		} else {
			log.warn("database is empty");
			return listEmpty;
		}
	}
	
	@Override
	public List<Technician> readAllTech(){
		List<Technician> listEmpty = new ArrayList<>();
		if (!dao.findAll().isEmpty()) {
			log.info("read all done in service");
			return daot.findAll();
		} else {
			log.warn("database is empty");
			return listEmpty;
		}
	}

	/**
	 * This method delete a person with its id if it exist in the database
	 * 
	 * @param id a Long id representing the hotel id
	 * 
	 * @return the boolean object, return True if the object has been delete, false
	 *         otherwise
	 */
	@Override
	public boolean deleteById(Long id) {
		try {
			dao.deleteById(id);
			log.info("person deleted in service");
			return true;
		} catch (Exception e) {
			log.error("This id does not exist");
			return false;
		}
	}

	/**
	 * This method delete a person with its Mail if it exist in the database
	 * 
	 * @param email String representing the email of the person
	 * @return the boolean object, return True if the object has been delete, false
	 *         otherwise
	 */
	@Override
	public boolean deleteByEmail(String email) {
		try {
			Person user = dao.findByMail(email);
			dao.deleteById(user.getId());
			log.info("person deleted in service");
			return true;
		} catch (Exception e) {
			log.error("This email does not exist");
			return false;
		}
	}

	/**
	 * This method Connect a person if its Mail and Pwd are correct Return null if
	 * it does not exist
	 * 
	 * @param email a string with an email value of the person
	 * @param pwd   a string with a password
	 * @return the person read
	 */
	@Override
	public Person login(String email, String pwd) {
		Person login = null;
		try {
			login = dao.findByMailAndPwd(email, pwd);
			log.info("login done in service");
			return login;
		} catch (Exception e) {
			log.warn("Email or password is wrong");
			return login;
		}
	}
}
