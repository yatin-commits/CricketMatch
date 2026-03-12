package com.ds.app.service;

import java.util.List;
import java.util.Map;

import com.ds.app.dto.request.BookingRequestDTO;
import com.ds.app.dto.response.BookingResponseDTO;
import com.ds.app.dto.response.MatchResponseDTO;
import com.ds.app.entities.Audience;
import com.ds.app.entities.Booking;
import com.ds.app.entities.CricketMatch;
import com.ds.app.exceptions.BookingIdNotFoundException;
import com.ds.app.exceptions.NoMatchOnThatDate;
import com.ds.app.exceptions.NotEnoughSpaceException;
import com.ds.app.exceptions.StadiumFullException;

public interface AudienceService {
	
	
	List< MatchResponseDTO> getUpcomingMatches();
	
	public BookingResponseDTO  bookTicket(BookingRequestDTO dto)  throws StadiumFullException, NotEnoughSpaceException, NoMatchOnThatDate;
	public Map<String, Object> getBookingStatus(int bookingId) throws BookingIdNotFoundException, NoMatchOnThatDate;
}
