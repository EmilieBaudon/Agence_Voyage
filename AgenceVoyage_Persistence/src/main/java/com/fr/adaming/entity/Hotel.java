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
@ToString
public class Hotel {

	/**
	 * @param Hotel ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	/**
	 * @param Hotel Name 
	 */
	@Column(unique=true, nullable=false)
	private String name;
	/**
	 * @param Hotel Description 
	 */
	private String desc;

	public Hotel(String name, String desc) {
		super();
		this.name = name;
		this.desc = desc;
	}

}