package com.fr.adaming.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *  This Class allows the conexion of a person to the application
 */
/**
 * 
 * @author victor
 *
 */
@Getter @Setter @NoArgsConstructor
public class LoginDto {	
	/**
	 * @param Person mail 
	 */
	private String mail;
	/**
	 * @param Person password 
	 */
	private String pwd;

}
