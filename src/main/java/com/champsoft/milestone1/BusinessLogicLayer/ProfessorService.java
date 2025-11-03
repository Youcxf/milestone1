package com.champsoft.milestone1.BusinessLogicLayer;

import com.champsoft.milestone1.DataAccessLayer.Professor;
import com.champsoft.milestone1.DataAccessLayer.ProfessorRepository;
import com.champsoft.milestone1.MappersLayer.ProfessorMapper;
import com.champsoft.milestone1.PresentationLayer.ProfessorRequestModel;
import com.champsoft.milestone1.PresentationLayer.ProfessorResponseModel;
import com.champsoft.milestone1.PresentationLayer.ProfessorWithDepartmentResponseDTO;
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
            professorResponseModels.add(professorMapper.toResponse(professor));
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

    public ProfessorWithDepartmentResponseDTO getProfessorWithDepartment(String id) {
        long idLong = Long.parseLong(id);
        Professor professor = professorRepository.findById(idLong)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with id: " + idLong + " not found"));
        return professorMapper.toProfessorWithDepartment(professor);
    }

    public ProfessorResponseModel createProfessor(ProfessorRequestModel professorData) {
        return this.professorMapper.toResponse(
                professorRepository.save(this.professorMapper.fromRequestModelToEntity(professorData))
        );
    }

    public ProfessorResponseModel updateProfessor(String id, ProfessorRequestModel professorData) {
        long idLong = Long.parseLong(id);

        // Check if professor exists
        if (!professorRepository.existsById(idLong)) {
            throw new ProfessorNotFoundException("Professor with id: " + idLong + " not found");
        }

        Professor newProfessor = this.professorMapper.fromRequestModelToEntity(professorData);
        newProfessor.setProfessorId(idLong);
        return professorMapper.toResponse(professorRepository.save(newProfessor));
    }

    public void deleteProfessor(String id) {
        long idLong = Long.parseLong(id);

        if (!professorRepository.existsById(idLong)) {
            throw new ProfessorNotFoundException("Professor with id: " + idLong + " not found");
        }

        professorRepository.deleteById(idLong);
    }
}