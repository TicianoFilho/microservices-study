package br.com.estudo.employeeservice.rest;

import br.com.estudo.employeeservice.dto.ApiResponseDTO;
import br.com.estudo.employeeservice.dto.EmployeeDTO;
import br.com.estudo.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeResource {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> save(@RequestBody EmployeeDTO dto) {

        EmployeeDTO savedEmployee = employeeService.save(dto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable(name = "id") long id, @RequestBody EmployeeDTO dto) {

        EmployeeDTO updatedEmployee = this.employeeService.update(id, dto);

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") long id) {

        this.employeeService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> getAll(Pageable pageable) {

        Page<EmployeeDTO> Employees = this.employeeService.findAll(pageable);

        return new ResponseEntity<>(Employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> getById(@PathVariable(name = "id") Long id) {

        ApiResponseDTO apiResponseDTO = this.employeeService.findById(id);

        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }
}
