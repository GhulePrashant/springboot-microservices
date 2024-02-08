package com.prashantghule.organizationservice.controller;

import com.prashantghule.organizationservice.dto.OrganizationDto;
import com.prashantghule.organizationservice.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;


    @Operation(
            summary = "Create Organization REST API",
            description = "This API is used to save Organization object in database."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Code CREATED"
    )
    @PostMapping("create")
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto createdOrganizationDto = organizationService.saveOrganization(organizationDto);

        return new ResponseEntity<>(createdOrganizationDto, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Create Organization REST API",
            description = "This API is used to get Organization object from database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status Code SUCCESS"
    )
    @GetMapping("{organization-code}")
    public ResponseEntity<OrganizationDto> findOrganizationByCode(@PathVariable("organization-code") String organizationCode){
        return new ResponseEntity<>(organizationService.getOrganizationByCode(organizationCode), HttpStatus.OK);
    }
}
