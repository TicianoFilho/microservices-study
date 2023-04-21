package br.com.study.organizationservice.rest;

import br.com.study.organizationservice.dto.OrganizationDTO;
import br.com.study.organizationservice.service.OrganizationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public ResponseEntity<Page<OrganizationDTO>> getAll(Pageable pageable) {

        Page<OrganizationDTO> organizationDTOS = organizationService.getAll(pageable);

        return ResponseEntity.ok(organizationDTOS);
    }

    @GetMapping("/{code}")
    public ResponseEntity<OrganizationDTO> getByCode(@PathVariable(name = "code") String code) {

        OrganizationDTO organizationDTO = organizationService.getByCode(code);

        return ResponseEntity.ok(organizationDTO);
    }
}
