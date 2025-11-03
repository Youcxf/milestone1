package com.champsoft.milestone1.MappersLayer;

import com.champsoft.milestone1.DataAccessLayer.Department;
import com.champsoft.milestone1.DataAccessLayer.DepartmentRepository;
import com.champsoft.milestone1.DataAccessLayer.Professor;
import com.champsoft.milestone1.PresentationLayer.DepartmentSummary;
import com.champsoft.milestone1.PresentationLayer.ProfessorRequestModel;
import com.champsoft.milestone1.PresentationLayer.ProfessorResponseModel;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {
    public DepartmentRepository departmentRepository;
    public ProfessorMapper(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public ProfessorResponseModel toResponse(Professor p)
    {
        Department department = p.getDepartment();
        DepartmentSummary departmentsummary = new DepartmentSummary();

        departmentsummary = new DepartmentSummary(department.getDepartmentId(),department.getDepartmentName(), department.getDepartmentBuilding());

        return new ProfessorResponseModel(p.getProfessorId(), p.getProfessorEmail(), p.getProfessorName(), p.getProfessorPhoneNumber(), departmentsummary.getId());
    }

    public Professor fromRequestModelToEntity(ProfessorRequestModel p)
    {
        Professor professor = new Professor();
        professor.setProfessorEmail(p.getEmail());
        professor.setProfessorName(p.getProfessorName());
        professor.setProfessorPhoneNumber(p.getProfessorPhoneNumber());
        professor.setDepartment(departmentRepository.getById(p.getDepartmentId()));

        return professor;
    }

}
