package br.com.study.organizationservice.service.impl;

import br.com.study.organizationservice.domain.Organization;
import br.com.study.organizationservice.dto.OrganizationDTO;
import br.com.study.organizationservice.exception.ResourceNotFoundException;
import br.com.study.organizationservice.repository.OrganizationRepository;
import br.com.study.organizationservice.service.OrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Page<OrganizationDTO> getAll(Pageable pageable) {
        Page<Organization> organizations = organizationRepository.findAll(pageable);

        List<OrganizationDTO> organizationDTOS = organizations.stream().map(organization -> {
            return mapper.map(organization, OrganizationDTO.class);
        }).toList();

        return new PageImpl<>(organizationDTOS, pageable, organizations.getTotalElements());
    }

    @Override
    public OrganizationDTO getByCode(String code) {

        Organization organization = organizationRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "code", code));

        return mapper.map(organization, OrganizationDTO.class);
    }


}
