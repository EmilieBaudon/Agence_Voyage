package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	/**
	 * @param Number of rooms 
	 */
	@Column(nullable = false)
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

	public Standing(int nbRoom, double priceChild, double priceAdult, String desc) {
		super();
		this.nbRoom = nbRoom;
		this.priceChild = priceChild;
		this.priceAdult = priceAdult;
		this.desc = desc;
	}

}