package com.prashantghule.departmentservice.service.impl;

import com.prashantghule.departmentservice.dto.DepartmentDto;
import com.prashantghule.departmentservice.entity.Department;
import com.prashantghule.departmentservice.exception.ResourceNotFoundException;
import com.prashantghule.departmentservice.mapper.DepartmentMapper;
import com.prashantghule.departmentservice.repository.DepartmentRepository;
import com.prashantghule.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
//        convert departmentDto to department JPA enitity

        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

//        convert savedDepartment into departmentDto

        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Optional<Department> department = departmentRepository.findByDepartmentCode(departmentCode);

        Department department1 = department.orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentCode));

        return DepartmentMapper.mapToDepartmentDto(department1);
    }

}
