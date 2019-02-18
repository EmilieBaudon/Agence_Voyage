package com.fr.adaming.restController;

import java.util.List;

import com.fr.adaming.entity.Person;
/**
 * 
 * @author EmilieBaudon
 *
 */
public interface IPersonController {
	
	public String create(Person person);
	
	public String update(Person person);
	
	public Person readByEmail(String email);
	
	public Person readById(Long id);
	
	public List<Person> readAll();
	
	public String deleteById(Long id);
	
	public String deleteByEmail(String Email);
	
	public String Login(String email, String pwd);
}
