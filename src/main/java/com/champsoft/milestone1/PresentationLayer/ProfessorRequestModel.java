package com.champsoft.milestone1.PresentationLayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfessorRequestModel {
    private String email;
    private String professorName;
    private String professorPhoneNumber;
    private Long departmentId;

}
