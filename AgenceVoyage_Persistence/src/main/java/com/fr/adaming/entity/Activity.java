package com.fr.adaming.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class summarizes all the activities that a traveler can do in the hotel he is staying.
 * The activities a person can do depend on the Standing of the hotel this person is staying at.
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
	
	Long id;
	/**
	 * @param  Activity Name
	 */
	String name;
	
	/**
	 * @param Activity description
	 */
	
	String desc;

	public Activity(String name, String desc) {
		super();
		this.name = name;
		this.desc = desc;
	}

}