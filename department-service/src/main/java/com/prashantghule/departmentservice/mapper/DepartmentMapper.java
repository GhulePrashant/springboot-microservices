package com.prashantghule.departmentservice.mapper;

import com.prashantghule.departmentservice.dto.DepartmentDto;
import com.prashantghule.departmentservice.entity.Department;

public class DepartmentMapper {
//   convert departmentDto to department JPA enitity
    public static Department mapToDepartment(DepartmentDto departmentDto) {
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        return department;
    }

//  convert department JPA enitity to departmentDto

    public static DepartmentDto mapToDepartmentDto(Department department){
        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }


}
