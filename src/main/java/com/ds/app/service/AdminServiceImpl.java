package com.ds.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.app.dto.request.MatchRequestDTO;
import com.ds.app.entities.Audience;
import com.ds.app.entities.CricketMatch;
import com.ds.app.exceptions.MatchAlreadyExistException;
import com.ds.app.exceptions.NoMatchOnThatDate;
import com.ds.app.repository.AudienceRepository;
import com.ds.app.repository.BookingRepository;
import com.ds.app.repository.CricketMatchRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	
	@Autowired
	private CricketMatchRepository cricketMatchRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private AudienceRepository audienceRepository;

	
	
	// Use Case A : Add a match
	
	@Override
	public CricketMatch addMatch(MatchRequestDTO dto) throws MatchAlreadyExistException{
		
		CricketMatch existing  = cricketMatchRepository.findByDateOfMatch(dto.getDateOfMatch());
		if (existing!=null) {
        throw new MatchAlreadyExistException("Match already exists for this date: " + dto.getDateOfMatch());
    }
		
		  CricketMatch match = new CricketMatch();
		  match.setDateOfMatch(dto.getDateOfMatch());
		  match.setCityName(dto.getCityName());
		  
		  return cricketMatchRepository.save(match);
		  
	}
	
	// Use Case B : View Audience details by matchDate
	
	@Override 
	public List<Audience>getAudienceByMatchDate(String date)
	{
		return audienceRepository.findByBooking_Match_DateOfMatch(date);
	}
	
    // Use Case d: View Seat Status by matchDate
	
	@Override
	public Map<String, Object> getSeatStatusByMatchDate(String matchDate) throws NoMatchOnThatDate {
	    CricketMatch match = cricketMatchRepository.findByDateOfMatch(matchDate);

	    if (match == null) {
	        throw new NoMatchOnThatDate("No match found on date: " + matchDate);
	    }

	    int bookedSeats = audienceRepository.countByBooking_Match_MatchNumber(match.getMatchNumber());
	    int availableSeats = match.getSeats() - bookedSeats;

	    Map<String, Object> status = new HashMap<>();
	    status.put("matchNumber", match.getMatchNumber());
	    status.put("city", match.getCityName());
	    status.put("date", match.getDateOfMatch());
	    status.put("totalSeats", match.getSeats());
	    status.put("bookedSeats", bookedSeats);
	    status.put("availableSeats", availableSeats);

	    return status;
	}

	
	
	
	
	

}
