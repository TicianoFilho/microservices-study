package br.com.estudo.departmentservice.rest;

import br.com.estudo.departmentservice.dto.DepartmentDTO;
import br.com.estudo.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{code}")
    public ResponseEntity<DepartmentDTO> getByCode(@PathVariable(name = "code") String code,
                                                   @RequestParam(name = "active") boolean active) {

        DepartmentDTO dto = this.departmentService.getByCode(code, active);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> update(@PathVariable(name = "id") long id, @RequestBody DepartmentDTO dto) {

        DepartmentDTO updatedDepartment = this.departmentService.update(id, dto);

        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") long id) {

        this.departmentService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<DepartmentDTO>> getAll(Pageable pageable,
                                                      @RequestParam(name = "active") boolean active) {

        Page<DepartmentDTO> departments = this.departmentService.findAll(pageable, active);

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}
