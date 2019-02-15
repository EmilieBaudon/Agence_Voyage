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
public class Customer extends Person {

	private String job;
	private Date jobStartDate;

	public Customer(String name, String firstName, Date birthDate, String adress, String mail, String pwd) {
		super(name, firstName, birthDate, adress, mail, pwd);
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String firstName, Date birthDate, String adress, String mail, String pwd, String job,
			Date jobStartDate) {
		super(name, firstName, birthDate, adress, mail, pwd);
		this.job = job;
		this.jobStartDate = jobStartDate;
	}

}
