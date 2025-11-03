package com.champsoft.milestone1.MappersLayer;

import com.champsoft.milestone1.DataAccessLayer.Department;
import com.champsoft.milestone1.PresentationLayer.DepartmentRequestModel;
import com.champsoft.milestone1.PresentationLayer.DepartmentResponseModel;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentResponseModel toResponse(Department d) {
        return new DepartmentResponseModel(
                d.getDepartmentId(),
                d.getDepartmentName(),
                d.getDepartmentBuilding()
        );
    }

    public Department fromRequestModelToEntity(DepartmentRequestModel d) {
        Department department = new Department();
        department.setDepartmentName(d.getName());
        department.setDepartmentBuilding(d.getBuilding());

        return department;
    }
}