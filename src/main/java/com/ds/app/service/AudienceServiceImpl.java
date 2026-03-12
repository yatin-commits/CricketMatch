package com.ds.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.app.dto.request.AudienceRequestDTO;
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
import com.ds.app.repository.AudienceRepository;
import com.ds.app.repository.BookingRepository;
import com.ds.app.repository.CricketMatchRepository;

@Service
public class AudienceServiceImpl implements AudienceService {

	@Autowired
	AudienceRepository audienceRepository;

	@Autowired
	CricketMatchRepository cricketMatchRepository;

	@Autowired
	BookingRepository bookingRepository;

	// Use Case a: Search upcoming match dates

	@Override
	public List<MatchResponseDTO> getUpcomingMatches() {

		String today = LocalDate.now().toString(); // yyyy-MM-dd

		// 1. Get upcoming matches from DB
		List<CricketMatch> matches = cricketMatchRepository
				.findByDateOfMatchGreaterThanEqualOrderByDateOfMatchAsc(today);

		// 2. Prepare response list
		List<MatchResponseDTO> responseList = new ArrayList<>();

		// 3. Loop through matches
		for (CricketMatch match : matches) {

			// total seats (fixed = 20)
			int totalSeats = match.getSeats();

			// booked seats for this match
			int bookedSeats = audienceRepository.countByBooking_Match_MatchNumber(match.getMatchNumber());

			int seatsLeft = totalSeats - bookedSeats;

			MatchResponseDTO dto = new MatchResponseDTO();
			dto.setMatchNumber(match.getMatchNumber());
			dto.setDateOfMatch(match.getDateOfMatch());
			dto.setCityName(match.getCityName());
			dto.setTotalSeats(totalSeats);
			dto.setSeatsLeft(seatsLeft);

			responseList.add(dto);
		}

		return responseList;
	}

	@Override
	public BookingResponseDTO bookTicket(BookingRequestDTO dto)
			throws StadiumFullException, NotEnoughSpaceException, NoMatchOnThatDate {

		CricketMatch match = cricketMatchRepository.findByDateOfMatch(dto.getMatchDate());

		if (match == null) {
			throw new NoMatchOnThatDate("No match found on date: " + dto.getMatchDate());
		}

		int bookedSeates = audienceRepository.countByBooking_Match_MatchNumber(match.getMatchNumber());
		if (bookedSeates >= match.getSeats()) {
			throw new StadiumFullException("Stadium is full! No seats available.");
		}

		if ((bookedSeates + dto.getAudienceList().size()) > match.getSeats()) {
			throw new NotEnoughSpaceException("Only " + (match.getSeats() - bookedSeates) + " seats remaining.");
		}

		Booking booking = new Booking();
		booking.setMatch(match);

		Booking savedBooking = bookingRepository.save(booking);
		int nextIndex = bookedSeates; 
		for (AudienceRequestDTO audienceDTO : dto.getAudienceList()) {
			Audience audience = new Audience();
			audience.setName(audienceDTO.getName());
			audience.setGender(audienceDTO.getGender());
			audience.setAge(audienceDTO.getAge());
			audience.setBooking(savedBooking);
			String seatLabel = toSeatLabel(nextIndex); // 1A, 2A, ... 5A, 1B, ...
	        audience.setSeatNumber(seatLabel);
	        nextIndex++;
			audienceRepository.save(audience);
		}

		return new BookingResponseDTO(savedBooking.getBookingId(), match.getDateOfMatch(), match.getCityName(),
				match.getMatchNumber(), dto.getAudienceList().size());
	}

	// Use Case c: Show Booking Status by ticket (bookingId)

	@Override
	public Map<String, Object> getBookingStatus(int bookingId) throws BookingIdNotFoundException {
	    Booking booking = bookingRepository.findById(bookingId).orElse(null);

	    if (booking == null) {
	        throw new BookingIdNotFoundException("No booking found for ID: " + bookingId);
	    }

	    CricketMatch match = booking.getMatch();
	    if (match == null) {
	        throw new BookingIdNotFoundException("Match details not found for booking ID: " + bookingId);
	    }

	    Map<String, Object> status = new HashMap<>();
	    status.put("bookingId", booking.getBookingId());
	    status.put("matchDate", match.getDateOfMatch());
	    status.put("city", match.getCityName());
	    status.put("matchNumber", match.getMatchNumber());
	    status.put("audienceCount", booking.getAllAudience() != null ? booking.getAllAudience().size() : 0);
	    status.put("audienceList", booking.getAllAudience());

	    

	    return status;
	}
	
	private String toSeatLabel(int zeroBasedIndex) {
	    int col = (zeroBasedIndex % 5) + 1;          // 1..5
	    int rowIndex = zeroBasedIndex / 5;           // 0 = 'A', 1 = 'B', ...
	    char rowLetter = (char) ('A' + rowIndex);    // A, B, C, ...
	    return col + String.valueOf(rowLetter);      // e.g., 1A, 2A, ...
	}

}
