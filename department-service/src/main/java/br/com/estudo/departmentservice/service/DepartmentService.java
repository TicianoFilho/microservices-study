package br.com.estudo.departmentservice.service;

import br.com.estudo.departmentservice.domain.Department;
import br.com.estudo.departmentservice.dto.DepartmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    public DepartmentDTO save(DepartmentDTO dto);

    public DepartmentDTO getByCode(String code);

    public DepartmentDTO update(long id, DepartmentDTO dto);

    public Page<DepartmentDTO> findAll(Pageable pageable);

    public void delete(long id);

}
