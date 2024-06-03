package com.tui.proof.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    @NotEmpty(message = "First name cannot be empty")
    @Size(max = 100, message = "First name cannot be longer than 100 characters")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    @Size(max = 100, message = "Last name cannot be longer than 100 characters")
    private String lastName;
    @NotEmpty(message = "Telephone cannot be empty")
    @Pattern(regexp = "^\\\\+?[0-9. ()-]{7,25}$", message = "Invalid telephone number")
    private String telephone;
}
