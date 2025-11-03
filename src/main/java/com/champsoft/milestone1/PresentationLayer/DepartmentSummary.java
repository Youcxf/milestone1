package com.champsoft.milestone1.PresentationLayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentSummary {
    private Long id;
    private String departmentName;
    private int departmentBuilding;
}