package com.prashantghule.employeeservice.service;

import com.prashantghule.employeeservice.dto.APIResponseDto;
import com.prashantghule.employeeservice.dto.EmployeeDto;
import org.springframework.stereotype.Service;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
