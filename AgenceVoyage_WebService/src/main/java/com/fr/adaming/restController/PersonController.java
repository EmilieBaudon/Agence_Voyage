package com.fr.adaming.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.Service.IPersonService;
import com.fr.adaming.dto.LoginDto;
import com.fr.adaming.dto.RegisterDto;
import com.fr.adaming.entity.Person;

/**
 * 
 * @author EmilieBaudon
 *
 */
@RestController
public class PersonController implements IPersonController{

	@Autowired
	@Qualifier ("PersonService")
	IPersonService service;
	
	/**
	 * @method import data from user to the service
	 * Use the Create method from person service
	 */
	@Override
	@RequestMapping(path="register", method=RequestMethod.POST)
	public String create(@RequestBody RegisterDto dto) {
		Person person = new Person(dto.getName(), dto.getFirstName(), dto.getBirthDate(), dto.getAdress(), dto.getMail(), dto.getPwd());
		Person result = service.create(person);
		if (result != null) {
			return "person has been created";
		} else {
			return "person could not be created";
		}
	}

	/**
	 * @method import data from user to the service
	 * Use the Update method from person service
	 */
	@Override
	@RequestMapping(path="update", method=RequestMethod.POST)
	public String update(@RequestBody RegisterDto dto) {
		Person person = new Person(dto.getName(), dto.getFirstName(), dto.getBirthDate(), dto.getAdress(), dto.getMail(), dto.getPwd());
		Person result = service.update(person);
		if (result != null) {
			return "person has been updated";
		} else {
			return "person could not be updated";
		} 
	}

	/**
	 * @method import data from user to the service
	 * Use the read by email method from person service
	 */
	@Override
	@RequestMapping(path="read/{email}", method=RequestMethod.GET)
	public Person readByEmail(String email) {
		return service.readByEmail(email);
	}

	/**
	 * @method import data from user to the service
	 * Use the read by id method from person service
	 */
	@Override
	@RequestMapping(path="read/{id}", method=RequestMethod.GET)
	public Person readById(Long id) {
		return service.readById(id);
	}

	/**
	 * @method import data from user to the service
	 * Use the read all method from person service
	 */
	@Override
	@RequestMapping(path="readAll", method=RequestMethod.GET)
	public List<RegisterDto> readAll() {
		List<Person> people = service.readAll();
		List<RegisterDto> dtos = new ArrayList<>();
		for (int i=0; i<people.size();i++) {
			dtos.add(new RegisterDto(people.get(i).getName(), people.get(i).getFirstName(), people.get(i).getBirthDate(), people.get(i).getAdress(), people.get(i).getMail(), people.get(i).getPwd()));
		}
		return dtos;
	}

	/**
	 * @method import data from user to the service
	 * Use the delete by id method from person service
	 */
	@Override
	@RequestMapping(path="delete/{id}", method=RequestMethod.DELETE)
	public String deleteById(Long id) {
		if (service.deleteById(id)) {
			service.deleteById(id);
			return "Person has been deleted";
		}else {
			return "Person could not be deleted";
		}
	}

	/**
	 * @method import data from user to the service
	 * Use the delete by email method from person service
	 */
	@Override
	@RequestMapping(path="delete/{email}", method=RequestMethod.DELETE)
	public String deleteByEmail(String email) {
		if (service.deleteByEmail(email)) {
			service.deleteByEmail(email);
			return "Person has been deleted";
		}else {
			return "Person could not be deleted";
		}
	}

	/**
	 * @method import data from user to the service
	 * Use the delete by email method from person service
	 */
	@Override
	@RequestMapping(path="login", method=RequestMethod.POST)
	public String Login(@RequestBody LoginDto login) {
		Person result = service.Login(login.getMail(), login.getPwd());
		if (result != null) {
			return "Login done";
		} else {
			return "Email or password are incorrect";
		}
	}

}
