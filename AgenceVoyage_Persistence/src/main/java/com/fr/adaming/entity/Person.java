package com.fr.adaming.entity;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * This class contains all the people in the database. 
 * The customers and the technicians. 
 * They have as attributes an id, a name, a premom, 
 * a date of birth, an address, an email, and a password.
 * 
 * @author Nicolas
 *
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Person {

	/**
	 * @param Person id
	 */
	private Long id;
	
	/**
	 * @param Person name
	 */
	private String name;
	
	/**
	 * @param Person first name
	 */
	private String firstName;
	
	/**
	 * @param Person date of birth 
	 */
	private Date birthDate;
	
	/**
	 * @param Person adress 
	 */
	private String Adress;
	
	/**
	 * @param Person mail 
	 */
	private String mail;
	
	/**
	 * @param Person password 
	 */
	private String pwd;

	public Person(String name, String firstName, Date birthDate, String adress, String mail, String pwd) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.birthDate = birthDate;
		Adress = adress;
		this.mail = mail;
		this.pwd = pwd;
	}

}