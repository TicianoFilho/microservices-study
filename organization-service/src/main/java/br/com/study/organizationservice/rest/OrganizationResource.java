package br.com.study.organizationservice.rest;

import br.com.study.organizationservice.dto.OrganizationDTO;
import br.com.study.organizationservice.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDTO> getById(@PathVariable(name = "id") Long id) {

        OrganizationDTO organizationDTO = this.organizationService.getById(id);

        return ResponseEntity.ok(organizationDTO);
    }
}
