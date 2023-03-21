package br.com.estudo.employeeservice.service;

import br.com.estudo.employeeservice.dto.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE") // This annotation will create dynamically the implementation for this interface.
public interface APIClient {

    @GetMapping("/api/departments/{code}")
    DepartmentDTO getDepartment(@PathVariable(name = "code") String code);

}
