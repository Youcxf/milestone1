package com.champsoft.milestone1.PresentationLayer;

import com.champsoft.milestone1.BusinessLogicLayer.DepartmentService;
import com.champsoft.milestone1.MappersLayer.DepartmentMapper;
import com.champsoft.milestone1.utilities.InvalidDepartmentDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    public DepartmentController(DepartmentService departmentService, DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    @GetMapping("/departments")
    public List<DepartmentResponseModel> getDepartments() {
        return this.departmentService.getAllDepartments();
    }

    @PostMapping("/departments")
    public ResponseEntity<DepartmentResponseModel> createDepartment(@RequestBody DepartmentRequestModel departmentData) {
        try {
            DepartmentResponseModel savedDepartment = this.departmentService.createDepartment(departmentData);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()                      // current request -> /departments
                    .path("/{id}")                             // append /{id}
                    .buildAndExpand(savedDepartment.getId())   // substitute {id}
                    .toUri();
            return ResponseEntity.created(location).body(savedDepartment);
        } catch (Exception e) {
            throw new InvalidDepartmentDataException("Invalid department data");
        }
    }

    @GetMapping("/departments/{id}")
    public DepartmentResponseModel getDepartmentById(@PathVariable String id) {
        try {
            return this.departmentService.getDepartmentById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.resolve(404), "Invalid department ID");
        }
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<DepartmentResponseModel> updateDepartment(@PathVariable String id, @RequestBody DepartmentRequestModel departmentData) {
        try {
            DepartmentResponseModel savedDepartment = this.departmentService.updateDepartment(id, departmentData);
            return ResponseEntity.ok(savedDepartment);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.resolve(404), "Invalid department data");
        }
    }

    @DeleteMapping("/departments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable String id) {
        try {
            this.departmentService.deleteDepartmentById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.resolve(404), "Invalid department ID");
        }
    }

    @GetMapping("/departments/{id}/professors")
    public List<ProfessorResponseModel> getProfessors(@PathVariable String id) {
        try {
            return departmentService.getProfessors(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.resolve(404), "Invalid department ID");
        }
    }
}