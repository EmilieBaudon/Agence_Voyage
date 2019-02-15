package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Activity;
import com.fr.adaming.entity.Technician;

public interface ITechnicianDao extends JpaRepository<Technician, Long>{

}
