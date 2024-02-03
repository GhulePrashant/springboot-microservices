package com.prashantghule.employeeservice.service.impl;

import com.prashantghule.employeeservice.dto.APIResponseDto;
import com.prashantghule.employeeservice.dto.DepartmentDto;
import com.prashantghule.employeeservice.dto.EmployeeDto;
import com.prashantghule.employeeservice.entity.Employee;
import com.prashantghule.employeeservice.exception.ResourceNotFoundException;
import com.prashantghule.employeeservice.mapper.EmployeeMapper;
import com.prashantghule.employeeservice.repository.EmployeeRepository;
import com.prashantghule.employeeservice.service.APIClient;
import com.prashantghule.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final String DEPARTMENTS_BASE_URL = "http://localhost:8080/api/departments/";
    private EmployeeRepository employeeRepository;
//    private RestTemplate restTemplate;

//    private WebClient webClient;

    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);

        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        Employee employee = optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

//        Using RestTemplate to communicate between microservices/APIs
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);

//        Using Webclient to call API
/*
        DepartmentDto departmentDto = webClient.get()
                .uri(DEPARTMENTS_BASE_URL + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
*/

        DepartmentDto departmentDto = apiClient.getDepartment(employeeDto.getDepartmentCode());

        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto);

        return apiResponseDto;

    }
}
