package com.fr.adaming.dto;

import javax.validation.constraints.NotNull;

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

public class BookingDtoWithId {

	/**
	 * @param id of the Booking
	 */
	@NotNull
	private Long id;

	/**
	 * @param nbrAdult
	 */
	private int nbrAdult;

	/**
	 * @param nbrChild
	 */
	private int nbrChild;

	/**
	 * @param totalPrice
	 */
	private Double totalPrice;

	/**
	 * @param pointAddFidelity
	 */
	private Long pointAddFidelity;

	/**
	 * @param customerDto
	 */

	private CustomerDtoWithId customerDto;

	/**
	 * @param travelDto
	 */

	private TravelDtoWithId travelDto;

}
