package com.fr.adaming.dto;

import java.time.LocalDate;
import java.util.List;

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
	private LocalDate jobStartDate;
	
	/**
	 * @param list of travel associated to a technician
	 */
	private List<TravelDto> ltravelDto;
	

}
