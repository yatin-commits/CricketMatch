package com.ds.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.app.entities.CricketMatch;

public interface CricketMatchRepository extends JpaRepository<CricketMatch, Integer>{
	
	CricketMatch findByDateOfMatch(String dateOfMatch);
	
	List<CricketMatch> findByDateOfMatchGreaterThanEqualOrderByDateOfMatchAsc(String today);

	
	

}
