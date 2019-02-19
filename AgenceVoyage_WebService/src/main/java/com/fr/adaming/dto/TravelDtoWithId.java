package com.fr.adaming.dto;

import java.time.LocalDate;
import java.util.List;

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

public class TravelDtoWithId {
	
	/**
	 * @param id of the travel
	 */
	@NotNull
	private Long id;

	
	/**
	 * @param number of nights
	 */
	@NotNull
	private int nbrNight;

	/**
	 * @param destiantion
	 */
	@NotNull
	private String destination;
	/**
	 * @param periodBegin
	 */
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING, timezone="CET")
	private LocalDate periodBegin;
	/**
	 * @param periodEnd
	 */
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING, timezone="CET")
	private LocalDate periodEnd;
	
	/**
	 * @param list of booking
	 */	
	private List<BookingDto> lbookingDto;
	/**
	 * @param list of flight
	 */	
	private List<FlightDto> lflightDto;
	
	/**
	 * @param hotelDto
	 */	
	private HotelDtoWithId hotelDto;
}