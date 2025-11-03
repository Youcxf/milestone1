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
@Getter
@Setter
@NoArgsConstructor
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long professorId;
    @NotBlank
    private String professorName;
    @Email
    private String professorEmail;
    @Positive
    private String professorPhoneNumber;
    @ManyToOne
    private Department department;

    public Professor(String professorEmail, String professorName, String professorPhoneNumber) {

        this.professorEmail = professorEmail;
        this.professorName = professorName;
        this.professorPhoneNumber = professorPhoneNumber;
    }


}
