package com.ds.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds.app.dto.request.MatchRequestDTO;
import com.ds.app.entities.Audience;
import com.ds.app.entities.CricketMatch;
import com.ds.app.exceptions.MatchAlreadyExistException;
import com.ds.app.exceptions.NoMatchOnThatDate;
import com.ds.app.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
		
	@Autowired
	AdminService adminService;
	
	// Endpoint :  /admin/match (POST)
	
	@PostMapping("/match")
	public ResponseEntity<CricketMatch>addMatch(@Valid @RequestBody MatchRequestDTO matchRequestDTO) throws MatchAlreadyExistException
	{
		CricketMatch saved = adminService.addMatch(matchRequestDTO);
		
		return ResponseEntity.ok(saved);
	}
	
	// EndPoint :  admin/audience?matchDate=2025-06-10 (GET)
	
	@GetMapping("/audience")
	public ResponseEntity<List<Audience>>getAudienceByMatchDate(@RequestParam String matchDate)
	{
		
		List<Audience>  audienceList = adminService.getAudienceByMatchDate(matchDate);
		return ResponseEntity.ok(audienceList);
	}
	
	@GetMapping("/seatStatus")
	public ResponseEntity<?>getSeatStatus(@RequestParam String matchDate) throws NoMatchOnThatDate
	{
		Map<String , Object> status = adminService.getSeatStatusByMatchDate(matchDate);
		return ResponseEntity.ok(status);

	}
	
	
	
	
	
	
	

}
