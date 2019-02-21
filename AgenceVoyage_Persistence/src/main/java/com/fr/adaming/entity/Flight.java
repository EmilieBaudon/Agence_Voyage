package com.fr.adaming.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The entity Travel is about the choices of customers It's linked to customers
 * and flight
 *
 *
 * @author Karguel
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Flight {

	/**
	 * @param Flight Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * @param Idplane
	 */
	private String idPlane;
	/**
	 * @param dateArrival
	 */
	private LocalDate dateArrival;
	/**
	 * @param dateDeaprture
	 */
	private LocalDate dateDeparture;
	/**
	 * @param airportDeparture
	 */
	private String airportDeparture;
	/**
	 * @param airportArrival
	 */
	private String airportArrival;
	
	/**
	 * @param price of the flight
	 */
	private Double price;

//	/**
//	 * @param Travel
//	 */
//	
//	private Travel travel;

	public Flight(String idPlane, LocalDate dateArrival, LocalDate dateDeparture, String airportDeparture,
			String airportArrival, Double price) {
		super();
		this.idPlane = idPlane;
		this.dateArrival = dateArrival;
		this.dateDeparture = dateDeparture;
		this.airportDeparture = airportDeparture;
		this.airportArrival = airportArrival;
		this.price = price;
	}
}
