package com.ds.app.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {

    @NotBlank(message = "matchDate is Required")
    private String matchDate;

    @NotEmpty(message = "audienceList is required")
    @Valid
    private List<AudienceRequestDTO> audienceList;

    
}