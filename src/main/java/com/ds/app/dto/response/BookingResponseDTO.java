package com.ds.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {

    private int bookingId;
    private String matchDate;
    private String city;
    private int matchNumber;
    private int audienceCount;

   
}