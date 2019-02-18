package com.fr.adaming.entity;

import java.util.Date;

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
	 * @param Travel
	 */
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "travel")
	private Travel travel;

	public Flight(String idPlane, Date dateArrival, Date dateDeparture, String airportDeparture, String airportArrival,
			Travel travel) {
		super();
		this.idPlane = idPlane;
		this.dateArrival = dateArrival;
		this.dateDeparture = dateDeparture;
		this.airportDeparture = airportDeparture;
		this.airportArrival = airportArrival;
		this.travel = travel;
	}

}
