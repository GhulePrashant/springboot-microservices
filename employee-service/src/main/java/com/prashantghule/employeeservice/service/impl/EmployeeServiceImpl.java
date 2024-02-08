package com.prashantghule.employeeservice.service.impl;

import com.prashantghule.employeeservice.dto.APIResponseDto;
import com.prashantghule.employeeservice.dto.DepartmentDto;
import com.prashantghule.employeeservice.dto.EmployeeDto;
import com.prashantghule.employeeservice.dto.OrganizationDto;
import com.prashantghule.employeeservice.entity.Employee;
import com.prashantghule.employeeservice.exception.ResourceNotFoundException;
import com.prashantghule.employeeservice.mapper.EmployeeMapper;
import com.prashantghule.employeeservice.repository.EmployeeRepository;
import com.prashantghule.employeeservice.service.APIClient;
import com.prashantghule.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private static final String DEPARTMENTS_BASE_URL = "http://localhost:8080/api/departments/";

    private static final String ORGANIZATIONS_BASE_URL = "http://localhost:8083/api/organizations/";

    private EmployeeRepository employeeRepository;
//    private RestTemplate restTemplate;

    private WebClient webClient;

    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);

        return savedEmployeeDto;
    }

//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        LOGGER.info("\nInside getEmployeeById()");
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        Employee employee = optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

//        Using RestTemplate to communicate between microservices/APIs
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);

//        Using Webclient to call API
        DepartmentDto departmentDto = webClient.get()
                .uri(DEPARTMENTS_BASE_URL + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

//        DepartmentDto departmentDto = apiClient.getDepartment(employeeDto.getDepartmentCode());

        OrganizationDto organizationDto = webClient.get()
                .uri(ORGANIZATIONS_BASE_URL + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();


        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto, organizationDto);

        return apiResponseDto;

    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception){
        LOGGER.info("\nInside getDefaultDepartment()");
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        Employee employee = optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);


        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationName("Default Org");
        organizationDto.setOrganizationDescription("Default Org Description");
        organizationDto.setCreatedDate(LocalDateTime.now());
        organizationDto.setOrganizationCode("Default001");

        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto, organizationDto);

        return apiResponseDto;
    }
}
