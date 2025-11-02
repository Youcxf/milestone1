package com.champsoft.milestone1.DataAccessLayer;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long professorId;
    @NotBlank
    private String professorName;
    @Email
    private String professorEmail;
    @Positive
    private int professorPhoneNumber;

    @ManyToOne
    private Department department;

    public Professor(Department department, String professorEmail, long professorId, String professorName, int professorPhoneNumber) {
        this.department = department;
        this.professorEmail = professorEmail;
        this.professorId = professorId;
        this.professorName = professorName;
        this.professorPhoneNumber = professorPhoneNumber;
    }

    public Professor() {

    }
}
