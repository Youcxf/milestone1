package com.champsoft.milestone1.PresentationLayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfessorSummary {
    private String name;
    private String email;
    private DepartmentSummary department;
}
