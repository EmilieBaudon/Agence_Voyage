package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Activity;
import com.fr.adaming.entity.Standing;
/**
 * 
 * @author victor
 *
 */
/**
 * This interface allows the persistence of data from the Class Standing
 */
public interface IStandingDao extends JpaRepository<Standing, Long>{

}
