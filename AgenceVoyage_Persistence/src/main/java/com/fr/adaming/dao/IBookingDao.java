package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Activity;
import com.fr.adaming.entity.Booking;

public interface IBookingDao extends JpaRepository<Booking, Long> {

}
