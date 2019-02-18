package com.fr.adaming.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fr.adaming.entity.Customer;
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

public class BookingDto {

	
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

	private Customer customer;

	/**
	 * @param travel
	 */
	
	private Travel travel;

	

}
