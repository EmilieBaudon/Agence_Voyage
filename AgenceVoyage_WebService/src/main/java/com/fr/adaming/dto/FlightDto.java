package com.fr.adaming.dto;

import java.util.Date;

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

public class FlightDto {

	
	/**
	 * @param Idplane
	 */
	private String idPlane;
	/**
	 * @param dateArrival
	 */
	private Date dateArrival;
	/**
	 * @param dateDeaprture
	 */
	private Date dateDeparture;
	/**
	 * @param airportDeparture
	 */
	private String airportDeparture;
	/**
	 * @param airportArrival
	 */
	private String airportArrival;

	/**
	 * @param TravelDto
	 */	
	private TravelDto travelDto;	

}
