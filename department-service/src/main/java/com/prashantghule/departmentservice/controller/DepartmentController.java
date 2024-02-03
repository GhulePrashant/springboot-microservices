package com.prashantghule.departmentservice.controller;

import com.prashantghule.departmentservice.dto.DepartmentDto;
import com.prashantghule.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

//    Build saveDepartment REST API

    @PostMapping("create")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);

        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

//    Build getDepartmentByCode REST API
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentByCode = departmentService.getDepartmentByCode(departmentCode);

        return new ResponseEntity<>(departmentByCode, HttpStatus.OK);
    }
}
