package com.prashantghule.departmentservice.repository;

import com.prashantghule.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByDepartmentCode(String code);
}
