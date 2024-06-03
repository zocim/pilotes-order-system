package com.tui.proof.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    @NotEmpty(message = "Street cannot be empty")
    @Size(max=255, message = "Street cannot be longer tan 255 characters")
    private String street;
    @NotEmpty(message = "Postcode cannot be empty")
    @Size(max = 10, message = "Postcode cannot be longer than 10 characters")
    private String postcode;
    @NotEmpty(message = "City cannot be empty")
    @Size(max = 100, message = "City cannot be longer than 100 characters")
    private String city;
    @NotEmpty(message = "Country cannot be empty")
    @Size(max = 100, message = "Country cannot be longer than 100 characters")
    private String country;
}
