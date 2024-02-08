package com.prashantghule.departmentservice.controller;

import com.prashantghule.departmentservice.dto.DepartmentDto;
import com.prashantghule.departmentservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "Department Controller"
)
@AllArgsConstructor
@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

//    Build saveDepartment REST API


    @Operation(
            summary = "Create Department REST API",
            description = "This API is used to save Department object in database."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Code CREATED"
    )
    @PostMapping("create")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);

        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

//    Build getDepartmentByCode REST API
    @Operation(
            summary = "Get Department REST API",
            description = "This API is used to Get Department object from database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Code SUCCESS"
    )
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentByCode = departmentService.getDepartmentByCode(departmentCode);

        return new ResponseEntity<>(departmentByCode, HttpStatus.OK);
    }
}
