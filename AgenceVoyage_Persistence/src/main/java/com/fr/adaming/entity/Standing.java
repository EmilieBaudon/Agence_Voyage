
package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class Standing {

	/**
	 * @param Standing ID
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	/**
	 * @param Number of rooms
	 */
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
	@Column(name="descr")
	private String desc;
	/**
	 * @param Hotel of the Standing 
	 */
	@JsonBackReference
	@OneToOne
	private Hotel hotel;
	

	/**
	 * 
	 * @param list of activities
	 */
	@JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Activity> lactivity;

	public Standing(int nbRoom, double priceChild, double priceAdult, String desc, Hotel hotel,
			List<Activity> lactivity) {
		super();
		this.nbRoom = nbRoom;
		this.priceChild = priceChild;
		this.priceAdult = priceAdult;
		this.desc = desc;
		this.hotel = hotel;
		this.lactivity = lactivity;
	}

}
