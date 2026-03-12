package com.ds.app.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CricketMatch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer matchNumber;

	private String dateOfMatch;

	private String cityName;

	private final int seats = 20;

	@OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
	private List<Booking> allBookings;
}
