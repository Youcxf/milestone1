package com.champsoft.milestone1.PresentationLayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentWithProfessorsResponseDTO {
    private Long id;
    private String departmentName;
    private int departmentBuilding;
    private List<ProfessorSummary> professors;
}