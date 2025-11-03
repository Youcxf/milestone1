package com.champsoft.milestone1.PresentationLayer;

import com.champsoft.milestone1.BusinessLogicLayer.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseModel>> getDepartments() {
        List<DepartmentResponseModel> departments = this.departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseModel> getDepartmentById(@PathVariable String id) {
        DepartmentResponseModel department = this.departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/{id}/professors")
    public ResponseEntity<DepartmentWithProfessorsResponseDTO> getDepartmentWithProfessors(@PathVariable String id) {
        DepartmentWithProfessorsResponseDTO department = this.departmentService.getDepartmentWithProfessors(id);
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public ResponseEntity<DepartmentResponseModel> createDepartment(@Valid @RequestBody DepartmentRequestModel departmentData) {
        DepartmentResponseModel savedDepartment = this.departmentService.createDepartment(departmentData);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDepartment.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedDepartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseModel> updateDepartment(
            @PathVariable String id,
            @Valid @RequestBody DepartmentRequestModel departmentData) {
        DepartmentResponseModel savedDepartment = this.departmentService.updateDepartment(id, departmentData);
        return ResponseEntity.ok(savedDepartment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteDepartment(@PathVariable String id) {
        this.departmentService.deleteDepartmentById(id);
        return ResponseEntity.noContent().build();
    }
}