package com.prashantghule.organizationservice.service.impl;

import com.prashantghule.organizationservice.dto.OrganizationDto;
import com.prashantghule.organizationservice.entity.Organization;
import com.prashantghule.organizationservice.mapper.OrganizationMapper;
import com.prashantghule.organizationservice.repository.OrganizationRepository;
import com.prashantghule.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization savedOrganization = organizationRepository.save(OrganizationMapper.mapToOrganization(organizationDto));

        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);

        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}
