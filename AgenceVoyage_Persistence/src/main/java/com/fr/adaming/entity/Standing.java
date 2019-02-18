package com.fr.adaming.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class describes the different kind of room qualities which can be found within one hotel.
 * For example, "Suites", "Premium room++", "regular"...
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

	public Standing(int nbRoom, double priceChild, double priceAdult, String desc) {
		super();
		this.nbRoom = nbRoom;
		this.priceChild = priceChild;
		this.priceAdult = priceAdult;
		this.desc = desc;
	}

}