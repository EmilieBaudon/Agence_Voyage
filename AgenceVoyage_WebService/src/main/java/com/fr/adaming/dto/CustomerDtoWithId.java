package com.fr.adaming.dto;

import java.util.List;

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

public class CustomerDtoWithId {

	/**
	 * @param id of the Customer
	 */
	@NotNull
	private Long id;

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
