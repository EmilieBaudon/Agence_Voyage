package com.fr.adaming.dto;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author victor
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {

	/**
	 * @param Activity Name
	 */
	@UniqueElements
	private String name;

	/**
	 * @param Activity description
	 */

	private String desc;

}