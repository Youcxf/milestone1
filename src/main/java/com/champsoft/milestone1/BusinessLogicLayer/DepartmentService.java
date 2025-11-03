package com.champsoft.milestone1.BusinessLogicLayer;

import com.champsoft.milestone1.DataAccessLayer.Department;
import com.champsoft.milestone1.DataAccessLayer.DepartmentRepository;
import com.champsoft.milestone1.DataAccessLayer.ProfessorRepository;
import com.champsoft.milestone1.MappersLayer.DepartmentMapper;
import com.champsoft.milestone1.MappersLayer.ProfessorMapper;
import com.champsoft.milestone1.PresentationLayer.DepartmentRequestModel;
import com.champsoft.milestone1.PresentationLayer.DepartmentResponseModel;
import com.champsoft.milestone1.PresentationLayer.DepartmentWithProfessorsResponseDTO;
import com.champsoft.milestone1.utilities.DepartmentNotFoundException;
import org.springframework.stereotype.Service;

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

    public DepartmentResponseModel getDepartmentById(String id) {
        long idLong = Long.parseLong(id);
        Optional<Department> department = departmentRepository.findById(idLong);

        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("Department with id: " + idLong + " not found");
        }
        return departmentMapper.toResponse(department.get());
    }

    public DepartmentWithProfessorsResponseDTO getDepartmentWithProfessors(String id) {
        long idLong = Long.parseLong(id);
        Department department = departmentRepository.findById(idLong)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with id: " + idLong + " not found"));
        return departmentMapper.toDepartmentWithProfessors(department);
    }

    public DepartmentResponseModel createDepartment(DepartmentRequestModel departmentData) {
        Department department = departmentMapper.fromRequestModelToEntity(departmentData);
        return departmentMapper.toResponse(departmentRepository.save(department));
    }

    public DepartmentResponseModel updateDepartment(String id, DepartmentRequestModel departmentData) {
        long idLong = Long.parseLong(id);

        // Check if department exists
        if (!departmentRepository.existsById(idLong)) {
            throw new DepartmentNotFoundException("Department with id: " + idLong + " not found");
        }

        Department department = departmentMapper.fromRequestModelToEntity(departmentData);
        department.setDepartmentId(idLong);
        return departmentMapper.toResponse(departmentRepository.save(department));
    }

    public void deleteDepartmentById(String id) {
        long longId = Long.parseLong(id);

        if(departmentRepository.existsById(longId)) {
            departmentRepository.deleteById(longId);
        } else {
            throw new DepartmentNotFoundException("Department with id: " + longId + " not found");
        }
    }
}