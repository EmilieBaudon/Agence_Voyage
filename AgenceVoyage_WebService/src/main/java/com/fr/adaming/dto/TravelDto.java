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

public class TravelDto {

	public TravelDto(@NotNull int nbrNight, @NotNull String destination, @NotNull LocalDate periodBegin,
			@NotNull LocalDate periodEnd, Long idhotelDto) {
		super();
		this.nbrNight = nbrNight;
		this.destination = destination;
		this.periodBegin = periodBegin;
		this.periodEnd = periodEnd;
		this.id_hotelDto = idhotelDto;
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
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING, timezone = "CET")
	private LocalDate periodBegin;
	/**
	 * @param periodEnd
	 */
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING, timezone = "CET")
	private LocalDate periodEnd;

	/**
	 * @param id_hotelDto
	 */
	private Long id_hotelDto;
}
