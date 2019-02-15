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
public class Person {

	private Long id;
	private String name;
	private String firstName;
	private Date birthDate;
	private String Adress;
	private String mail;
	private String pwd;

}