package br.com.study.organizationservice.service;

import br.com.study.organizationservice.dto.OrganizationDTO;

import java.util.Optional;

public interface OrganizationService {

    OrganizationDTO save(OrganizationDTO organization);
    OrganizationDTO getById(Long id);
}
