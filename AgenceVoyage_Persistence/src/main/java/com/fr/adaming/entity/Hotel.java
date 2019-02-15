package com.fr.adaming.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Hotel {

	Long id;
	String name;
	String desc;

	public Hotel(String name, String desc) {
		super();
		this.name = name;
		this.desc = desc;
	}

}