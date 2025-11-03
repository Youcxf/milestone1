package com.champsoft.milestone1.PresentationLayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentResponseModel {
    Long id;
    String departmentName;
    int departmentBuilding;
}