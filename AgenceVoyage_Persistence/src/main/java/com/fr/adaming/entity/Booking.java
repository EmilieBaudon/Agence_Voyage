package com.fr.adaming.entity;

import java.util.List;

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
/**
*The entity Booking is about the choices of customers 
*It's linked to customers and travel
*
*
*@author Karguel
*/


@Entity
@Getter @Setter @NoArgsConstructor
public class Booking {

	/**
	 * @param Booking Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * @param nbrAdult 
	 */
	private int nbrAdult;
	/**
	 * @param nbrChild
	 */
	private int nbrChild;
	/**
	 * @param totalPrice
	 */
	private Double totalPrice;
	/**
	 * @param pointAddFidelity
	 */
	private Long pointAddFidelity;
	/**
	 * @param customer
	 */
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer")
	private Customer customer;
	
	
	/**
	 * @param travel
	 */
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "travel")
	private Travel travel;

	public Booking(int nbrAdult, int nbrChild, Double totalPrice, Long pointAddFidelity, Customer customer,
			Travel travel) {
		super();
		this.nbrAdult = nbrAdult;
		this.nbrChild = nbrChild;
		this.totalPrice = totalPrice;
		this.pointAddFidelity = pointAddFidelity;
		this.customer = customer;
		this.travel = travel;
	}
	
	
}
