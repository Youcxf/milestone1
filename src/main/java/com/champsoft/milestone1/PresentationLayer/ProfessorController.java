package com.champsoft.milestone1.PresentationLayer;

import com.champsoft.milestone1.BusinessLogicLayer.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "https://milestoneyoucefelias-j2cb.vercel.app"})
@RestController
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponseModel>> getProfessors() {
        List<ProfessorResponseModel> professors = this.professorService.getProfessors();
        return ResponseEntity.ok(professors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseModel> getProfessorById(@PathVariable String id) {
        ProfessorResponseModel professor = this.professorService.getProfessorById(id);
        return ResponseEntity.ok(professor);
    }

    @GetMapping("/{id}/departments")
    public ResponseEntity<ProfessorWithDepartmentResponseDTO> getProfessorWithDepartment(@PathVariable String id) {
        ProfessorWithDepartmentResponseDTO professor = this.professorService.getProfessorWithDepartment(id);
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<ProfessorResponseModel> createProfessor(@Valid @RequestBody ProfessorRequestModel professorData) {
        ProfessorResponseModel savedProfessor = this.professorService.createProfessor(professorData);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProfessor.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedProfessor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseModel> updateProfessor(
            @PathVariable String id,
            @Valid @RequestBody ProfessorRequestModel professorData) {
        ProfessorResponseModel savedProfessor = this.professorService.updateProfessor(id, professorData);
        return ResponseEntity.ok(savedProfessor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteProfessor(@PathVariable String id) {
        this.professorService.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }
}