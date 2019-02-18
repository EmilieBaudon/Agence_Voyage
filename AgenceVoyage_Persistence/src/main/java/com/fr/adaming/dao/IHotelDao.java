package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Activity;
import com.fr.adaming.entity.Hotel;
/**
 * 
 * @author victor
 *
 */
/**
 * This interface allows the persistence of data from the Class Hotel
 */
public interface IHotelDao extends JpaRepository<Hotel, Long> {

}
