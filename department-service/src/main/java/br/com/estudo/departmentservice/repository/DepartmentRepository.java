package br.com.estudo.departmentservice.repository;

import br.com.estudo.departmentservice.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
