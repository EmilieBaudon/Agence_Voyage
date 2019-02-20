package com.fr.adaming.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {	
	/**
	 * @param Login mail
	 */
	@Email
	private String mail;
	/**
	 * @param Login pwd
	 * Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character
	 */
	@NotNull
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$")
	private String pwd;

}
