package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Travel;

public interface ITravelDao extends JpaRepository<Travel, Long> {

}
