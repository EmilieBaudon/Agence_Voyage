package com.fr.adaming.dto;

import java.time.LocalDate;
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

public class TravelDto {
	
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
	private LocalDate periodBegin;
	/**
	 * @param periodEnd
	 */
	@NotNull
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
	private HotelDto hotelDto;
}
