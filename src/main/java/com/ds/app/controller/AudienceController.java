package com.ds.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.ds.app.service.AudienceService;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/audience")
public class AudienceController {

	@Autowired
	private AudienceService audienceService;

	// GET /audience/matches - Search upcoming match dates

	@GetMapping("/matches")
	public ResponseEntity<List< MatchResponseDTO>> getUpcomingMatches() {
		List< MatchResponseDTO> allMatches = audienceService.getUpcomingMatches();
		return ResponseEntity.ok(allMatches);
	}

	// POST /audience/book?matchDate=2025-06-10 - Book ticket for match based on
	// matchDate

	@PostMapping("/book")
	public ResponseEntity<?> bookTicket(@Valid @RequestBody BookingRequestDTO bookingRequestDTO) throws StadiumFullException, NotEnoughSpaceException, NoMatchOnThatDate {
		BookingResponseDTO  booking = audienceService.bookTicket(bookingRequestDTO);
		return ResponseEntity.ok(
				Map.of("message", "Booking successful!", "bookingId", booking.getBookingId(), "matchDate", booking.getMatchDate()));
	}

	// GET /audience/booking/{bookingId} - Show Booking Status by ticket Id

	@GetMapping("/booking/{bookingId}")
	public ResponseEntity<?> getBookingStatus(@PathVariable int bookingId) 
	        throws BookingIdNotFoundException, NoMatchOnThatDate {
	    Map<String, Object> status = audienceService.getBookingStatus(bookingId);
	    return ResponseEntity.ok(status);
	}
}
