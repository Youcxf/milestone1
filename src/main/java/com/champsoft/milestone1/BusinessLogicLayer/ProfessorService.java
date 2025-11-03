package com.champsoft.milestone1.BusinessLogicLayer;

import com.champsoft.milestone1.DataAccessLayer.Department;
import com.champsoft.milestone1.DataAccessLayer.Professor;
import com.champsoft.milestone1.DataAccessLayer.ProfessorRepository;
import com.champsoft.milestone1.MappersLayer.ProfessorMapper;
import com.champsoft.milestone1.PresentationLayer.DepartmentSummary;
import com.champsoft.milestone1.PresentationLayer.ProfessorRequestModel;
import com.champsoft.milestone1.PresentationLayer.ProfessorResponseModel;
import com.champsoft.milestone1.utilities.ProfessorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    public List<ProfessorResponseModel> getProfessors() {
        List<Professor> professors = this.professorRepository.findAll();
        List<ProfessorResponseModel> professorResponseModels = new ArrayList<>();

        for(Professor professor : professors) {
            Department temp = professor.getDepartment();
            DepartmentSummary departmentSummary = new DepartmentSummary();
            departmentSummary.setId(temp.getDepartmentId());
            departmentSummary.setBuilding(temp.getDepartmentBuilding());

            professorResponseModels.add(new ProfessorResponseModel(
                    professor.getProfessorId(),
                    professor.getProfessorEmail(),
                    professor.getProfessorName(),
                    professor.getProfessorPhoneNumber(),
                    departmentSummary.getId()
            ));
        }
        return professorResponseModels;
    }

    public ProfessorResponseModel getProfessorById(String id) {
        long idLong = Long.parseLong(id);
        Optional<Professor> professor = Optional.ofNullable(
                this.professorRepository.findById(idLong)
                        .orElseThrow(() -> new ProfessorNotFoundException("Professor with id: " + idLong + " not found"))
        );
        return this.professorMapper.toResponse(professor.get());
    }

    public ProfessorResponseModel createProfessor(ProfessorRequestModel professorData) {
        return this.professorMapper.toResponse(
                professorRepository.save(this.professorMapper.fromRequestModelToEntity(professorData))
        );
    }

    public ProfessorResponseModel updateProfessor(String id, ProfessorRequestModel professorData) {
        Professor newProfessor = this.professorMapper.fromRequestModelToEntity(professorData);
        newProfessor.setProfessorId(Long.parseLong(id));
        return professorMapper.toResponse(professorRepository.save(newProfessor));
    }

    public Professor deleteProfessor(String id) {
        long idLong = Long.parseLong(id);
        Professor professor = this.professorRepository.findById(idLong).get();
        professorRepository.delete(professor);
        return professor;
    }
}