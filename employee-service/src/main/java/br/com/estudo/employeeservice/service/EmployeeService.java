package br.com.estudo.employeeservice.service;

import br.com.estudo.employeeservice.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    public EmployeeDTO save(EmployeeDTO dto);

    public EmployeeDTO update(long id, EmployeeDTO dto);

    public Page<EmployeeDTO> findAll(Pageable pageable);

    public EmployeeDTO findById(long id);

    public void delete(long id);
}
