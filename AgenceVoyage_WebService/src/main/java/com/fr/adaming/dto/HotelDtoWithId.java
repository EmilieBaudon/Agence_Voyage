
package com.fr.adaming.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fr.adaming.entity.Standing;

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
	private String name;
	/**
	 * @param Hotel Description 
	 */
	private String desc;
	/**
	 * @param ltravelDto
	 */	
	List <TravelDtoWithId> ltravelDto;
	
	/**
	 *  @param lstandingDto  the standing of the hotel
	 * 
	 */	
	private Standing lstandingDto;


}