package com.champsoft.milestone1.PresentationLayer;

import com.champsoft.milestone1.BusinessLogicLayer.ProfessorService;
import com.champsoft.milestone1.MappersLayer.ProfessorMapper;
import com.champsoft.milestone1.utilities.InvalidProfessorDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProfessorController {

    private final ProfessorService professorService;
    private final ProfessorMapper professorMapper;

    public ProfessorController(ProfessorService professorService, ProfessorMapper professorMapper) {
        this.professorService = professorService;
        this.professorMapper = professorMapper;
    }

    @GetMapping("/professors")
    public List<ProfessorResponseModel> getProfessors() {
        List<ProfessorResponseModel> professors = this.professorService.getProfessors();
        return professors;
    }

    @PostMapping("/professors")
    public ResponseEntity<ProfessorResponseModel> createProfessor(@RequestBody ProfessorRequestModel professorData) {
        try {
            ProfessorResponseModel savedProfessor = this.professorService.createProfessor(professorData);
            // build Location: /professors/{id}
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()                        // current request -> /professors
                    .path("/{id}")                               // append /{id}
                    .buildAndExpand(savedProfessor.getId())      // substitute {id}
                    .toUri();
            return ResponseEntity.created(location).body(savedProfessor);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.resolve(404), "Invalid professor data");
        }
    }

    @GetMapping("/professors/{id}")
    public ProfessorResponseModel getProfessorById(@PathVariable String id) {
        try {
            ProfessorResponseModel professorID = this.professorService.getProfessorById(id);
            return professorID;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.resolve(404), "Invalid professor ID");
        }
    }

    @PutMapping("/professors/{id}")
    public ResponseEntity<ProfessorResponseModel> updateProfessor(@PathVariable String id, @RequestBody ProfessorRequestModel professorData) {
        try {
            ProfessorResponseModel savedProfessor = this.professorService.updateProfessor(id, professorData);
            return ResponseEntity.ok(savedProfessor);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.resolve(404), "Invalid professor data");
        }
    }

    @DeleteMapping("/professors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfessor(@PathVariable String id) {
        try {
            this.professorService.deleteProfessor(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.resolve(404), "Invalid professor ID");
        }
    }
}