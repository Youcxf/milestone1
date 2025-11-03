package com.champsoft.milestone1.BusinessLogicLayer;

import com.champsoft.milestone1.DataAccessLayer.Department;
import com.champsoft.milestone1.DataAccessLayer.DepartmentRepository;
import com.champsoft.milestone1.DataAccessLayer.Professor;
import com.champsoft.milestone1.DataAccessLayer.ProfessorRepository;
import com.champsoft.milestone1.MappersLayer.DepartmentMapper;
import com.champsoft.milestone1.MappersLayer.ProfessorMapper;
import com.champsoft.milestone1.PresentationLayer.DepartmentRequestModel;
import com.champsoft.milestone1.PresentationLayer.DepartmentResponseModel;
import com.champsoft.milestone1.PresentationLayer.ProfessorResponseModel;
import com.champsoft.milestone1.utilities.DepartmentNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ProfessorRepository professorRepository;
    private final DepartmentMapper departmentMapper;
    private final ProfessorMapper professorMapper;

    public DepartmentService(DepartmentRepository departmentRepository,
                             ProfessorRepository professorRepository,
                             DepartmentMapper departmentMapper,
                             ProfessorMapper professorMapper) {
        this.departmentRepository = departmentRepository;
        this.professorRepository = professorRepository;
        this.departmentMapper = departmentMapper;
        this.professorMapper = professorMapper;
    }

    public List<DepartmentResponseModel> getAllDepartments() {
        List<Department> departments = this.departmentRepository.findAll();
        List<DepartmentResponseModel> departmentResponseModels = new ArrayList<>();

        for (Department department : departments) {
            departmentResponseModels.add(departmentMapper.toResponse(department));
        }
        return departmentResponseModels;
    }

    public DepartmentResponseModel getDepartmentById(@PathVariable String id) {
        long idLong = Long.parseLong(id);
        Optional<Department> department = departmentRepository.findById(idLong);

        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("Department not found");
        }
        return departmentMapper.toResponse(department.get());
    }

    public DepartmentResponseModel createDepartment(DepartmentRequestModel departmentData) {
        Department department = departmentMapper.fromRequestModelToEntity(departmentData);
        return departmentMapper.toResponse(departmentRepository.save(department));
    }

    public DepartmentResponseModel updateDepartment(String id, DepartmentRequestModel departmentData) {
        Department department = departmentMapper.fromRequestModelToEntity(departmentData);
        department.setDepartmentId(Long.parseLong(id));
        return departmentMapper.toResponse(departmentRepository.save(department));
    }

    public void deleteDepartmentById(String id) {
        long longId = Long.parseLong(id);

        if(departmentRepository.existsById(longId)) {
            departmentRepository.deleteById(longId);
        } else {
            throw new DepartmentNotFoundException("Department not found");
        }
    }

    public List<ProfessorResponseModel> getProfessors(String id) {
        Department department = departmentRepository.findById(Long.parseLong(id)).get();
        List<ProfessorResponseModel> professorResponseModels = new ArrayList<>();

        department.getProfessors().forEach(professor -> {
            professorResponseModels.add(professorMapper.toResponse(professor));
        });

        return professorResponseModels;
    }
}