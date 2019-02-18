package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class describes the different kind of room qualities which can be found
 * within one hotel. For example, "Suites", "Premium room++", "regular"...
 * 
 * It includes its description, number of rooms, cost for children and adults.
 * 
 * @author Quentin
 * 
 *
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Standing {

	/**
	 * @param Standing ID
	 */
	Long id;
	/**
	 * @param Number of rooms
	 */
	int nbRoom;
	/**
	 * @param Room cost for one child
	 */
	double priceChild;
	/**
	 * @param Room cost for one adult
	 */
	double priceAdult;
	/**
	 * @param Description of the Standing category
	 */
	String desc;
	/**
	 * @param hotel associated to Standing
	 * @author Karguel
	 */
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hotel")
	private Hotel hotel;

	/**
	 * 
	 * @param list of activities
	 */
	@JsonManagedReference
	@ManyToMany (fetch = FetchType.EAGER)
	private List <Activity> lactivity;
	
	
	public Standing(int nbRoom, double priceChild, double priceAdult, String desc) {
		super();
		this.nbRoom = nbRoom;
		this.priceChild = priceChild;
		this.priceAdult = priceAdult;
		this.desc = desc;
	}

}