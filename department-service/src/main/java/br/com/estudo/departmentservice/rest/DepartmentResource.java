package br.com.estudo.departmentservice.rest;

import br.com.estudo.departmentservice.dto.DepartmentDTO;
import br.com.estudo.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentResource {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDTO> save(@RequestBody DepartmentDTO dto) {
        DepartmentDTO savedDepartment = departmentService.save(dto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }
}
