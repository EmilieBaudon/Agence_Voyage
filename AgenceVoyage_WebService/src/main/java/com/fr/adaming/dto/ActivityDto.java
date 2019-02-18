package com.fr.adaming.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.UniqueElements;

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