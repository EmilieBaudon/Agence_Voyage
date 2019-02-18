package com.fr.adaming.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fr.adaming.entity.Person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author victor
 *
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TechnicianDto {

	/**
	 * @param Technician's job
	 */
	private String job;

	/**
	 * @param Technician's job start date
	 */
	private LocalDate jobStartDate;
	
	

}
