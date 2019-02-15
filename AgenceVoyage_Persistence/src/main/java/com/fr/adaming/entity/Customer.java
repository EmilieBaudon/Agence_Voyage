package com.fr.adaming.entity;

import java.util.Date;

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
public class Technician extends Person {

	private String card;
	private Long fidelityPoint;

	public Technician(String name, String firstName, Date birthDate, String adress, String mail, String pwd) {
		super(name, firstName, birthDate, adress, mail, pwd);
		// TODO Auto-generated constructor stub
	}

	public Technician(String name, String firstName, Date birthDate, String adress, String mail, String pwd,
			String card, Long fidelityPoint) {
		super(name, firstName, birthDate, adress, mail, pwd);
		this.card = card;
		this.fidelityPoint = fidelityPoint;
	}

}
