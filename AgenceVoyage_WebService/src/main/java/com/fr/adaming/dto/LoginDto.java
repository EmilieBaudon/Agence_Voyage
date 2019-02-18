package com.fr.adaming.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *  This Class allows the connection of a person to the application
 */
/**
 * 
 * @author victor
 *
 */
@Getter @Setter @NoArgsConstructor
public class LoginDto {	
	/**
	 * @param Login mail
	 */
	@Email
	private String mail;
	/**
	 * @param Login pwd
	 */
	@NotNull
	private String pwd;

}
