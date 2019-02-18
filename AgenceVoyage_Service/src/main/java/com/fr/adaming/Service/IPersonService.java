package com.fr.adaming.Service;

import java.util.List;

import com.fr.adaming.entity.Person;

public interface IPersonService {

	public Person create(Person person);
	public Person update(Person person);
	public Person readByEmail(String email);
	public Person readById(Long id);
	public List<Person> readAll();
	public void deleteById(Long id);
	public void deleteByEmail(String Email);
	public Person Login(String email, String pwd);
}
