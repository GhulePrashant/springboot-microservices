package com.prashantghule.organizationservice.mapper;

import com.prashantghule.organizationservice.dto.OrganizationDto;
import com.prashantghule.organizationservice.entity.Organization;

public class OrganizationMapper {

    public static OrganizationDto mapToOrganizationDto(Organization organization){

        return new OrganizationDto(organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getCreatedDate());
    }

    public static Organization mapToOrganization(OrganizationDto organizationDto){

        return new Organization(organizationDto.getId(),
                organizationDto.getOrganizationName(),
                organizationDto.getOrganizationDescription(),
                organizationDto.getOrganizationCode(),
                organizationDto.getCreatedDate());
    }
}
