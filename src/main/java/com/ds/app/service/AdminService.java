package com.ds.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ds.app.dto.request.MatchRequestDTO;
import com.ds.app.entities.Audience;
import com.ds.app.entities.CricketMatch;
import com.ds.app.exceptions.MatchAlreadyExistException;
import com.ds.app.exceptions.NoMatchOnThatDate;
import com.ds.app.repository.AudienceRepository;
import com.ds.app.repository.BookingRepository;
import com.ds.app.repository.CricketMatchRepository;

public interface AdminService {
	
	
	public CricketMatch addMatch(MatchRequestDTO dto) throws MatchAlreadyExistException;
	
	public List<Audience>getAudienceByMatchDate(String date);
	
	public Map<String , Object>getSeatStatusByMatchDate(String date) throws NoMatchOnThatDate;
	
	
	
	
	

}
