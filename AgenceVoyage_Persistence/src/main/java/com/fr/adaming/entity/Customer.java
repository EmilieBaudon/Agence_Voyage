package com.fr.adaming.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * This class contains all the customers in the database. It has
 * attributes such as a bank card, and loyalty points, acquired during a travel
 * reservation.
 *
 * @author Nicolas
 *
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer extends Person {

	/**
	 * @param Customer's card
	 */
	private String card;

	/**
	 * @param Customer's fidelity points
	 */
	private Long fidelityPoint;

	/**
	 * @param lbooking
	 * @author Karguel
	 */
	@JsonManagedReference
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	List<Booking> lbooking;

	public Customer(String name, String firstName, LocalDate birthDate, String adress, String mail, String pwd,
			String card) {
		super(name, firstName, birthDate, adress, mail, pwd);
		this.card = card;

	}

	public Customer(String name, String firstName, LocalDate birthDate, String adress, String mail, String pwd) {
		super(name, firstName, birthDate, adress, mail, pwd);
	}
}
