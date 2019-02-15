package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Activity;
import com.fr.adaming.entity.Person;

public interface IPersonDao extends JpaRepository<Person, Long>{
	
	public Person findByMailAndPwd(String mail,String pwd);

}
