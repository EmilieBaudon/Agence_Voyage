
package com.fr.adaming.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

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

public class HotelDtoWithId {

	/**
	 * @param id of the Hotel
	 */
	@NotNull
	private Long id;
	
	/**
	 * @param Hotel Name 
	 */
	@UniqueElements
	private String name;
	/**
	 * @param Hotel Description 
	 */
	private String desc;
	/**
	 * @param ltravelDto
	 */	
	List <TravelDto> ltravelDto;
	
	/**
	 *  @param lstandingDto 
	 * 
	 */	
	List <StandingDto> lstandingDto;


}