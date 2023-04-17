package br.com.study.organizationservice.rest;

import br.com.study.organizationservice.dto.OrganizationDTO;
import br.com.study.organizationservice.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationResource {

    private final OrganizationService organizationService;

    public OrganizationResource(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<OrganizationDTO> save(@RequestBody OrganizationDTO dto) {

        OrganizationDTO organizationDTO = this.organizationService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(organizationDTO);
    }
}
