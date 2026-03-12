package com.ds.app.dto.request;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AudienceRequestDTO {

    @NotBlank(message = "Name Is Required")
    private String name;

    @NotNull(message = "Gender Is Required")
    private String gender;

    @Min(value = 1, message = "Age Cannot be less than 1")
    @NotNull(message = "Age Is Required")
    private Integer age;
}