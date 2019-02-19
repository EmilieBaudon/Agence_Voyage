package com.fr.adaming.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * This class contains all the technicians in the database. It attributes a job,
 * and the date of the first day of job.
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
	private LocalDate jobStartDate;

	/**
	 * @param list of travel associated to a technician
	 * @author Karguel
	 */
	@JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Travel> ltravel;

	public Technician(String name, String firstName, LocalDate birthDate, String adress, String mail, String pwd) {
		super(name, firstName, birthDate, adress, mail, pwd);
	}

	public Technician(String name, String firstName, LocalDate birthDate, String adress, String mail, String pwd,
			String job, LocalDate jobStartDate, List<Travel> ltravel) {
		super(name, firstName, birthDate, adress, mail, pwd);
		this.job = job;
		this.jobStartDate = jobStartDate;
		this.ltravel = ltravel;
	}

}
