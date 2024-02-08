package com.prashantghule.organizationservice.service;

import com.prashantghule.organizationservice.dto.OrganizationDto;
import com.prashantghule.organizationservice.entity.Organization;

public interface OrganizationService {

    public OrganizationDto saveOrganization(OrganizationDto organizationDto);

    public OrganizationDto getOrganizationByCode(String organizationCode);
}
