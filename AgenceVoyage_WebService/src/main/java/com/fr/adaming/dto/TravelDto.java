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
	
	public TravelDto(@NotNull int nbrNight, @NotNull String destination, @NotNull LocalDate periodBegin,
			@NotNull LocalDate periodEnd, List<BookingDto> lbookingDto, List<FlightDto> lflightDto, HotelDto hotelDto) {
		super();
		this.nbrNight = nbrNight;
		this.destination = destination;
		this.periodBegin = periodBegin;
		this.periodEnd = periodEnd;
		this.lbookingDto = lbookingDto;
		this.lflightDto = lflightDto;
		this.hotelDto = hotelDto;
	}

	/**
	 * @param id of the travel
	 */
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
