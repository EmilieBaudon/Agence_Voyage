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
 * This class summarizes all the activities that a traveler can do in the hotel
 * he is staying. The activities a person can do depend on the Standing of the
 * hotel this person is staying at.
 * 
 * @author Quentin
 *
 */

@Entity
@Getter
@Setter
@NoArgsConstructor

@ToString
public class Activity {

	/**
	 * @param Activity ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * @param Activity Name
	 */
	@Column(unique = true)
	private String name;

	/**
	 * @param Activity description
	 */
	@Column(name="descr")
	private String desc;

	public Activity(String name, String desc) {
		super();
		this.name = name;
		this.desc = desc;
	}

}