package com.fr.adaming.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fr.adaming.entity.Travel;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor

public class FlightDto {

	
	/**
	 * @param Idplane
	 */
	private String idPlane;
	/**
	 * @param dateArrival
	 */
	private Date dateArrival;
	/**
	 * @param dateDeaprture
	 */
	private Date dateDeparture;
	/**
	 * @param airportDeparture
	 */
	private String airportDeparture;
	/**
	 * @param airportArrival
	 */
	private String airportArrival;

	/**
	 * @param TravelDto
	 */	
	private Travel travel;	

}
