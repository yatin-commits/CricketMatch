package com.ds.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.app.entities.Audience;
import com.ds.app.entities.Booking;
import com.ds.app.entities.CricketMatch;

public interface AudienceRepository extends JpaRepository<Audience, Integer> {
	
	List<Audience>findByBooking_Match_DateOfMatch(String date);
	
	 int countByBooking_Match_MatchNumber(Integer matchNumber);
	
}
