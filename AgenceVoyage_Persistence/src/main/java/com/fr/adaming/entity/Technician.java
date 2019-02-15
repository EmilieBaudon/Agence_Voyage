package com.fr.adaming.entity;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** 
 * 
 * This class contains all the technicians in the database. 
 * It attributes a job, and the date of the first day of job.
 *  
 * @author Nicolas
 *
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Technician extends Person {

	/**
	 * @param Technician's job
	 */
	private String job;

	/**
	 * @param Technician's job start date
	 */
	private Date jobStartDate;

	public Technician(String name, String firstName, Date birthDate, String adress, String mail, String pwd) {
		super(name, firstName, birthDate, adress, mail, pwd);
		// TODO Auto-generated constructor stub
	}

	public Technician(String name, String firstName, Date birthDate, String adress, String mail, String pwd, String job,
			Date jobStartDate) {
		super(name, firstName, birthDate, adress, mail, pwd);
		this.job = job;
		this.jobStartDate = jobStartDate;
	}

}
