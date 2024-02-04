package com.prashantghule.employeeservice.service;

import com.prashantghule.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Eureka server provides Load Balancer module to us.
 * in @FeignClient annotation, use name attribute instead of url(hardcoded url value) and value.
 * In the name attribute give the value which is configured in application.properties file of that microservice
 * you can find it in localhost:8761 eureka UI also
 *
 * By using the microservice's registered name, if one of the instance is down,
 * eureka will balance the load and give us response from available instance
 */

//@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
}
