package com.ds.app.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResponseDTO {

    private int matchNumber;
    private String dateOfMatch;
    private String cityName;
    private int totalSeats;
    private int seatsLeft;
}