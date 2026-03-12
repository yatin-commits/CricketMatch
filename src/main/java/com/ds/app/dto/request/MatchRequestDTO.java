package com.ds.app.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchRequestDTO {

    @NotBlank(message = "dateOfMatch is Required")
    private String dateOfMatch;

    @NotBlank(message = "cityName is Required")
    private String cityName;

}