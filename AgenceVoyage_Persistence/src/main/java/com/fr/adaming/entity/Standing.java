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
public class Standing {

	Long id;
	int nbRoom;
	double priceChild;
	double priceAdult;
	String desc;

	public Standing(int nbRoom, double priceChild, double priceAdult, String desc) {
		super();
		this.nbRoom = nbRoom;
		this.priceChild = priceChild;
		this.priceAdult = priceAdult;
		this.desc = desc;
	}

}