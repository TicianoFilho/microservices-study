package br.com.estudo.employeeservice.service.impl;

import br.com.estudo.employeeservice.domain.Employee;
import br.com.estudo.employeeservice.dto.EmployeeDTO;
import br.com.estudo.employeeservice.repository.EmployeeRepository;
import br.com.estudo.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class EmployeeServiceImpl extends AbstractBaseClass implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO save(EmployeeDTO dto) {
        
        Employee employee = mapper.map(dto, Employee.class);

        this.saveBusinessLogic(employee);

        return mapper.map(this.employeeRepository.save(employee), EmployeeDTO.class);
    }

    private void saveBusinessLogic(Employee employee) {

        employee.setId(null);
        employee.setActive(true);
        employee.setDeleted(false);
        employee.setCreatedDate(LocalDateTime.now());
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
