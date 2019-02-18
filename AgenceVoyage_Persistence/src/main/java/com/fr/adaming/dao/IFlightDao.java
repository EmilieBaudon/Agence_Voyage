package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Activity;
import com.fr.adaming.entity.Flight;
/**
 * 
 * @author victor
 *
 */
/**
 * This interface allows the persistence of data from the Class Flight
 */
public interface IFlightDao extends JpaRepository<Flight, Long> {

}
