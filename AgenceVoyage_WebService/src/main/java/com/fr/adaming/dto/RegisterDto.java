package com.fr.adaming.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *  This Class allows a Personn to register to a dataBase
 */
/**
 * 
 * @author victor
 *
 */
@Getter @Setter @NoArgsConstructor
public class RegisterDto {
	
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

}
