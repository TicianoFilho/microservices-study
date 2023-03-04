package br.com.estudo.departmentservice.service.impl;

import br.com.estudo.departmentservice.domain.Department;
import br.com.estudo.departmentservice.dto.DepartmentDTO;
import br.com.estudo.departmentservice.repository.DepartmentRepository;
import br.com.estudo.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl extends AbstractBaseClass implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO save(DepartmentDTO dto) {

        Department department = mapper.map(dto, Department.class);

        department.setId(null);

        return mapper.map(departmentRepository.save(department), DepartmentDTO.class);
    }
}
