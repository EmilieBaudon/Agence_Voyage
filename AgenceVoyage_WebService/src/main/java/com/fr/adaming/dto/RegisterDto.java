package com.fr.adaming.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  This Class allows a Person to register to a dataBase
 */
/**
 * 
 * @author victor
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class RegisterDto {
	/**
	 * @param Person name
	 */
	@NotNull
	private String name;
	/**
	 * @param Person first name
	 */
	@NotNull
	private String firstName;
	/**
	 * @param Person date of birth
	 */
	@NotNull
	private LocalDate birthDate;
	/**
	 * @param Person adress
	 */
	@NotNull
	private String Adress;
	/**
	 * @param Person mail
	 */
	@Email
	private String mail;
	/**
	 * @param Person password
	 */
	@NotNull
	private String pwd;
	public RegisterDto(@NotNull String name, @NotNull String firstName, @NotNull LocalDate birthDate,
			@NotNull String adress, @Email String mail, @NotNull String pwd) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.birthDate = birthDate;
		Adress = adress;
		this.mail = mail;
		this.pwd = pwd;
	}

	
	
}
