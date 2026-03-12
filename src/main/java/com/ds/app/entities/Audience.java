package com.ds.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Audience {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer audienceId;
	private String name;
	private String gender;
	private int age;
	private String seatNumber;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
	
	
}
