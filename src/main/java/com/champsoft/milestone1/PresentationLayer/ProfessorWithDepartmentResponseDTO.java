package com.champsoft.milestone1.PresentationLayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfessorWithDepartmentResponseDTO {
    private Long id;
    private String email;
    private String professorName;
    private String professorPhoneNumber;
    private DepartmentSummary department;
}