package com.fr.adaming.dto;

import java.time.LocalDate;
import java.util.List;

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
	 * @param Technician's job
	 */
	private String job;

	/**
	 * @param Technician's job start date
	 */
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING, timezone="CET")
	private LocalDate jobStartDate;
	
	/**
	 * @param list of travel associated to a technician
	 */
	private List<TravelDto> ltravelDto;
	

}
