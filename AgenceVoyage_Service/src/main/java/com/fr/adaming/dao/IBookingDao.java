package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Activity;

public interface IBookingDao extends JpaRepository<Booking, Long> {

}
