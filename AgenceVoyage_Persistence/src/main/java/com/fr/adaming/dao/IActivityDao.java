package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Activity;
/**
 * 
 * @author victor
 *
 */
/**
 * This interface allows the persistence of data from the Class Activity.
 */
public interface IActivityDao extends JpaRepository<Activity, Long> {

}
