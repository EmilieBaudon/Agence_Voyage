package com.fr.adaming.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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

public class CustomerDtoWithId {

	/**
	 * @param id of the Customer
	 */
	@NotNull
	private Long id;
	
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

	/**
	 * @param Customer's card
	 */

	private String card;

	/**
	 * @param Customer's fidelity points
	 */
	private Long fidelityPoint;

	/**
	 * @param lbookingDto
	 */
	List<BookingDto> lbookingDto;
}
