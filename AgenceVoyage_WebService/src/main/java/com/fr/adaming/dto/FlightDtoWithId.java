package com.fr.adaming.dto;

import java.time.LocalDate;

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

public class FlightDtoWithId {

	/**
	 * @param id of the Flight
	 */
	@NotNull
	private Long id;

	/**
	 * @param Idplane
	 */
	private String idPlane;
	/**
	 * @param dateArrival
	 */
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING, timezone = "CET")
	private LocalDate dateArrival;
	/**
	 * @param dateDeaprture
	 */
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING, timezone = "CET")
	private LocalDate dateDeparture;
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
	private TravelDtoWithId travelDto;

}
