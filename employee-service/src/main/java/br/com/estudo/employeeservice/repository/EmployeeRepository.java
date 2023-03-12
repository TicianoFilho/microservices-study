package br.com.estudo.employeeservice.repository;

import br.com.estudo.employeeservice.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Optional<Employee> findByIdAndActive(long id, boolean active);

    public Page<Employee> findAllByActive(Pageable pageable, boolean active);
}
