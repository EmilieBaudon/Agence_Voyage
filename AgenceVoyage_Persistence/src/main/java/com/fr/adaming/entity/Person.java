package com.fr.adaming.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * <<<<<<< HEAD This class contains all the people in the database. The
 * customers and the technicians. They have as attributes an id, a name, a
 * premom, a date of birth, an address, an email, and a password. ======= This
 * class contains all the people in the database. The customers and the
 * technicians. They have as attributes an id, a name, a premom, a date of
 * birth, an address, an email, and a password. >>>>>>>
 * ddc23f6f7023aa199ffcc6350af138b33ca34d81
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * @param Person name
	 */

	@Column(nullable = false)
	private String name;

	/**
	 * @param Person first name
	 */
	private String firstName;

	/**
	 * @param Person date of birth
	 */
	private LocalDate birthDate;

	/**
	 * @param Person adress
	 */
	private String Adress;

	/**
	 * @param Person mail
	 */
	@Column(unique = true)
	private String mail;

	/**
	 * @param Person password
	 */
	private String pwd;

	public Person(String name, String firstName, LocalDate birthDate, String adress, String mail, String pwd) {

		super();
		this.name = name;
		this.firstName = firstName;
		this.birthDate = birthDate;
		Adress = adress;
		this.mail = mail;
		this.pwd = pwd;
	}

}
