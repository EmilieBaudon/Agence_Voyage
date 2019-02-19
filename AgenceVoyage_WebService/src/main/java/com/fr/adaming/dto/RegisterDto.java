package com.fr.adaming.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

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

@AllArgsConstructor

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
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING, timezone="CET")
	private LocalDate birthDate;
	/**
	 * @param Person adress
	 */
	@NotNull
	private String adress;
	/**
	 * @param Person mail
	 */
	@Email
	private String mail;
	/**
	 * @param Person password
	 */
	private String pwd;

	
	
}
