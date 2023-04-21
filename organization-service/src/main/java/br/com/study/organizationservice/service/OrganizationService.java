package br.com.study.organizationservice.service;

import br.com.study.organizationservice.dto.OrganizationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface OrganizationService {

    OrganizationDTO save(OrganizationDTO organization);

    Page<OrganizationDTO> getAll(Pageable pageable);

    OrganizationDTO getByCode(String code);
}
