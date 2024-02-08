package com.prashantghule.employeeservice.controller;


import com.prashantghule.employeeservice.dto.APIResponseDto;
import com.prashantghule.employeeservice.dto.EmployeeDto;
import com.prashantghule.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private EmployeeService employeeService;


    @Operation(
            summary = "Create Employee REST API",
            description = "This API is used to save Employee object in database."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Code CREATED"
    )
    @PostMapping("create")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get Employee REST API",
            description = "This API is used to get Employee object from database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Code SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") Long employeeId){
        APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);

        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
