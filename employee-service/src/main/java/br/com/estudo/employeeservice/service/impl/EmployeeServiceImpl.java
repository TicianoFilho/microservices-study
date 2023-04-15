package br.com.estudo.employeeservice.service.impl;

import br.com.estudo.employeeservice.domain.Employee;
import br.com.estudo.employeeservice.dto.ApiResponseDTO;
import br.com.estudo.employeeservice.dto.DepartmentDTO;
import br.com.estudo.employeeservice.dto.EmployeeDTO;
import br.com.estudo.employeeservice.exception.ResourceNotFoundException;
import br.com.estudo.employeeservice.repository.EmployeeRepository;
import br.com.estudo.employeeservice.service.APIClient;
import br.com.estudo.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class EmployeeServiceImpl extends AbstractBaseClass implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private APIClient apiClient;

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

        Employee employee = this.getActiveEmployeeById(id);

        this.updatePayLoadFields(dto, employee);

        EmployeeDTO updatedEmployeeDto = mapper.map(this.employeeRepository.save(employee), EmployeeDTO.class);

        return updatedEmployeeDto;
    }

    @Override
    public Page<EmployeeDTO> findAll(Pageable pageable) {

        Page<Employee> employees = this.employeeRepository.findAllByActive(pageable, true);

        return employees.map(department -> mapper.map(department, EmployeeDTO.class));
    }

    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiResponseDTO findById(long id) {

        Employee employee = this.getActiveEmployeeById(id);

        DepartmentDTO departmentDTO = apiClient.getDepartment(employee.getDepartmentCode()); // Here is the communication between microservices through Spring Cloud Open Feign.

        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        apiResponseDTO.setEmployee(mapper.map(employee, EmployeeDTO.class));
        apiResponseDTO.setDepartment(departmentDTO);

        return apiResponseDTO;
    }

    @Override
    public void delete(long id) {

        Employee employee = this.getActiveEmployeeById(id);

        this.deleteBusinessLogic(employee);

        this.employeeRepository.save(employee);
    }

    private void deleteBusinessLogic(Employee employee) {

        employee.setActive(Boolean.FALSE);
        employee.setDeleted(Boolean.TRUE);
    }

    private Employee getActiveEmployeeById(long id) {
        return this.employeeRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", String.valueOf(id)));
    }

    private void updatePayLoadFields(EmployeeDTO dto, Employee employee) {

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setUpdatedDate(LocalDateTime.now());
        employee.setDepartmentCode(dto.getDepartmentCode());
    }

    // Method created for circuitBreaker to get a default department. See this.findById method.
    public ApiResponseDTO getDefaultDepartment(long id, Exception exception) {

        Employee employee = this.getActiveEmployeeById(id);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("default department name");
        departmentDTO.setCode("DF001");
        departmentDTO.setDescription("Default department description");
        departmentDTO.setDeleted(Boolean.FALSE);
        departmentDTO.setActive(Boolean.TRUE);
        departmentDTO.setCreateDate(LocalDateTime.now());

        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        apiResponseDTO.setEmployee(mapper.map(employee, EmployeeDTO.class));
        apiResponseDTO.setDepartment(departmentDTO);

        return apiResponseDTO;
    }
}
