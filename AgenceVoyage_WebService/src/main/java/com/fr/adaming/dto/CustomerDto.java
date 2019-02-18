package com.fr.adaming.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fr.adaming.entity.Booking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author victor
 *
 */


@Getter @Setter
@NoArgsConstructor

public class CustomerDto {

	/**
	 * @param Customer's card
	 */
	
	private String card;

	/**
	 * @param Customer's fidelity points
	 */
	private Long fidelityPoint;	

}
