
package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * 
 * This class describes the different hotels in which a traveler can stay.
 * It is linked to the "Standing" class which describes the standing quality of the hotel (price, nb of rooms...).
 * 
 * @author Quentin
 *
 */


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hotel {

	/**
	 * @param Hotel ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * @param Hotel Name 
	 */
	@Column(unique=true, nullable=false)
	private String name;
	/**
	 * @param Hotel Description 
	 */
	@Column(name="descr")
	private String desc;
	/**
	 * @param The travel's list associated to the hostel
	 * @author Karguel
	 */
	@JsonManagedReference
	@OneToMany(mappedBy="hotel" , fetch = FetchType.LAZY)
	List <Travel> ltravel;
	
	/**
	 *  @param The standing's list associated to the hostel
	 * 
	 */
	@JsonManagedReference
	@OneToOne
	private Standing standing;

	public Hotel(String name, String desc, List<Travel> ltravel, Standing standing) {
		super();
		this.name = name;
		this.desc = desc;
		this.ltravel = ltravel;
		this.standing = standing;
	}
	
	
	


}