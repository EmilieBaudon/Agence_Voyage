
package com.fr.adaming.dto;

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

public class StandingDto {

	
	/**
	 * @param Number of rooms
	 */
	@NotNull
	private int nbRoom;
	/**
	 * @param Room cost for one child
	 */
	private double priceChild;
	/**
	 * @param Room cost for one adult
	 */
	private double priceAdult;
	/**
	 * @param Description of the Standing category
	 */
	private String desc;
	/**
	 * @param hotel associated to Standing	 
	 */	
	private HotelDto hotelDto;
	/**
	 * 
	 * @param list of activities
	 */
	private List<ActivityDto> lactivityDto;

}
