
package com.fr.adaming.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

public class HotelDto {

	
	/**
	 * @param Hotel Name 
	 */
	@UniqueElements
	private String name;
	/**
	 * @param Hotel Description 
	 */
	private String desc;


}