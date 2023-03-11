package br.com.estudo.employeeservice.service.impl;

import br.com.estudo.employeeservice.dto.EmployeeDTO;
import br.com.estudo.employeeservice.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends AbstractBaseClass implements EmployeeService {

    @Override
    public EmployeeDTO save(EmployeeDTO dto) {
        return null;
    }

    @Override
    public EmployeeDTO update(long id, EmployeeDTO dto) {
        return null;
    }

    @Override
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public EmployeeDTO findById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
