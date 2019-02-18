
package com.fr.adaming.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author victor
 *
 */


@Getter
@Setter
@NoArgsConstructor

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
	private HotelDto hotel;

}
