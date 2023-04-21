package br.com.estudo.departmentservice.repository;

import br.com.estudo.departmentservice.domain.Department;
import br.com.estudo.departmentservice.dto.DepartmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public Optional<Department> findByCode(String code);

    public Optional<Department> findByIdAndActive(long id, boolean active);

    public Optional<Department> findByCodeAndActive(String code, boolean active);

    public Page<Department> findAllByActive(Pageable pageable, boolean active);

    public boolean existsByCode(String code);

}
