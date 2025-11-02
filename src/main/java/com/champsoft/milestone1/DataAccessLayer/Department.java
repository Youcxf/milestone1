package com.champsoft.milestone1.DataAccessLayer;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

import java.util.Set;


@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    @NotBlank
    private String departmentName;
    private int departmentBuilding;


    @OneToMany(mappedBy = "departmentId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Professor> professors = new HashSet<>();

    public Department(int departmentBuilding, Long departmentId, String departmentName, Set<Professor> professors) {
        this.departmentBuilding = departmentBuilding;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.professors = professors;
    }
}
