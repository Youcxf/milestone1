package com.champsoft.milestone1.PresentationLayer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfessorRequestModel {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Professor name is required")
    private String professorName;

    @NotBlank(message = "Phone number is required")
    private String professorPhoneNumber;

    @NotNull(message = "Department ID is required")
    private Long departmentId;
}