package com.fr.adaming.entity;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
=======
import java.util.Date;

import javax.persistence.Entity;
>>>>>>> ddc23f6f7023aa199ffcc6350af138b33ca34d81

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
<<<<<<< HEAD
 * This class contains all the customers in the database. It has attributes such
 * as a bank card, and loyalty points, acquired during a travel reservation.
=======
 *This class contains all the customers in the database. 
 *It has attributes such as a bank card, and loyalty points, 
 *acquired during a travel reservation.
>>>>>>> ddc23f6f7023aa199ffcc6350af138b33ca34d81
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
<<<<<<< HEAD

=======
	
>>>>>>> ddc23f6f7023aa199ffcc6350af138b33ca34d81
	/**
	 * @param Customer's fidelity points
	 */
	private Long fidelityPoint;
<<<<<<< HEAD
	/**
	 * @param lbooking
	 * @author Karguel
	 */
	@JsonManagedReference
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	List<Booking> lbooking;

	public Customer(String name, String firstName, LocalDate birthDate, String adress, String mail, String pwd) {
=======

	public Customer(String name, String firstName, Date birthDate, String adress, String mail, String pwd) {
>>>>>>> ddc23f6f7023aa199ffcc6350af138b33ca34d81
		super(name, firstName, birthDate, adress, mail, pwd);
		// TODO Auto-generated constructor stub
	}

<<<<<<< HEAD
	public Customer(String name, String firstName, LocalDate birthDate, String adress, String mail, String pwd,
			String card, Long fidelityPoint, List<Booking> lbooking) {
		super(name, firstName, birthDate, adress, mail, pwd);
		this.card = card;
		this.fidelityPoint = fidelityPoint;
		this.lbooking = lbooking;
=======
	public Customer(String name, String firstName, Date birthDate, String adress, String mail, String pwd,
			String card, Long fidelityPoint) {
		super(name, firstName, birthDate, adress, mail, pwd);
		this.card = card;
		this.fidelityPoint = fidelityPoint;
>>>>>>> ddc23f6f7023aa199ffcc6350af138b33ca34d81
	}

}