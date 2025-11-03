package com.champsoft.milestone1.MappersLayer;

import com.champsoft.milestone1.DataAccessLayer.Department;
import com.champsoft.milestone1.PresentationLayer.DepartmentRequestModel;
import com.champsoft.milestone1.PresentationLayer.DepartmentResponseModel;
import com.champsoft.milestone1.PresentationLayer.DepartmentWithProfessorsResponseDTO;
import com.champsoft.milestone1.PresentationLayer.ProfessorSummary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentMapper {

    public DepartmentResponseModel toResponse(Department d) {
        return new DepartmentResponseModel(
                d.getDepartmentId(),
                d.getDepartmentName(),
                d.getDepartmentBuilding()
        );
    }

    public Department fromRequestModelToEntity(DepartmentRequestModel d) {
        Department department = new Department();
        department.setDepartmentName(d.getDepartmentName());  // Changed
        department.setDepartmentBuilding(d.getDepartmentBuilding());  // Changed
        return department;
    }

    public DepartmentWithProfessorsResponseDTO toDepartmentWithProfessors(Department d) {
        List<ProfessorSummary> professorSummaries = d.getProfessors().stream()
                .map(p -> new ProfessorSummary(p.getProfessorId(), p.getProfessorName(), p.getProfessorEmail()))
                .toList();

        return new DepartmentWithProfessorsResponseDTO(
                d.getDepartmentId(),
                d.getDepartmentName(),
                d.getDepartmentBuilding(),
                professorSummaries
        );
    }
}