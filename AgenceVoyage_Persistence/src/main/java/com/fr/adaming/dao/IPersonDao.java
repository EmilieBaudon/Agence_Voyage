package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Activity;
import com.fr.adaming.entity.Person;
/**
 * 
 * @author victor
 *
 */
/**
 * This interface allows the persistence of data from the Class Person
 */
public interface IPersonDao extends JpaRepository<Person, Long>{
	
	public Person findByMailAndPwd(String mail,String pwd);
	public Person findByMail (String mail);

}
