package com.prashantghule.employeeservice.mapper;

import com.prashantghule.employeeservice.dto.EmployeeDto;
import com.prashantghule.employeeservice.entity.Employee;

public class EmployeeMapper {

    //   convert employeeDto to employee JPA enitity
    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode()
        );
        return employee;
    }

//  convert employee JPA enitity to employeeDto

    public static EmployeeDto mapToEmployeeDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganizationCode()
        );
        return employeeDto;
    }


}
