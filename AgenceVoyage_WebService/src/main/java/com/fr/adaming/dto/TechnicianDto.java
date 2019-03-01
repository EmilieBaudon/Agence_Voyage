package com.fr.adaming.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author victor
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TechnicianDto {

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
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, timezone = "CET")
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
	 * @param Person password Minimum eight characters, at least one uppercase
	 *               letter, one lowercase letter, one number and one special
	 *               character
	 */
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*]).{8,}$")
	private String pwd;
	/**
	 * @param Technician's job
	 */
	private String job;

	/**
	 * @param Technician's job start date
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, timezone = "CET")
	private LocalDate jobStartDate;
}
