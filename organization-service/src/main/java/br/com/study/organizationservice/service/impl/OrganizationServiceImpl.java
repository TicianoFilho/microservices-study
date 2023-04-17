package br.com.study.organizationservice.service.impl;

import br.com.study.organizationservice.domain.Organization;
import br.com.study.organizationservice.dto.OrganizationDTO;
import br.com.study.organizationservice.repository.OrganizationRepository;
import br.com.study.organizationservice.service.OrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl extends AbstractBaseClass implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(ModelMapper mapper, OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public OrganizationDTO save(OrganizationDTO dto) {

        Organization organization = this.organizationRepository.save(mapper.map(dto, Organization.class));

        return mapper.map(organization, OrganizationDTO.class);
    }
}
