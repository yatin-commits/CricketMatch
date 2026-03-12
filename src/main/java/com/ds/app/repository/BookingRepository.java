package com.ds.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.app.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	
	Integer countByMatch_MatchNumber(Integer matchId);

}
