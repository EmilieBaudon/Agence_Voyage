package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Travel;

/**
 * 
 * @author victor
 *
 */
/**
 * This interface allows the persistence of data from the Class Travel
 */

public interface ITravelDao extends JpaRepository<Travel, Long> {

}
