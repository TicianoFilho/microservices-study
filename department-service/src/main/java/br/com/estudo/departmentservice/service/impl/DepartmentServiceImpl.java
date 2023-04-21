package br.com.estudo.departmentservice.service.impl;

import br.com.estudo.departmentservice.domain.Department;
import br.com.estudo.departmentservice.dto.DepartmentDTO;
import br.com.estudo.departmentservice.exception.ResourceAlreadyExistsException;
import br.com.estudo.departmentservice.exception.ResourceNotFoundException;
import br.com.estudo.departmentservice.repository.DepartmentRepository;
import br.com.estudo.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl extends AbstractBaseClass implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public DepartmentDTO save(DepartmentDTO dto) {

        Department department = mapper.map(dto, Department.class);

        this.saveBusinessLogic(department);

        return mapper.map(this.departmentRepository.save(department), DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO getByCode(String code) {

        Department department = this.getActiveDepartmentByCode(code);

        return mapper.map(department, DepartmentDTO.class);
    }

    @Transactional
    @Override
    public DepartmentDTO update(long id, DepartmentDTO dto) {

        Department department = this.getActiveDepartmentById(id);

        this.updatePayLoadFields(dto, department);

        DepartmentDTO updatedDepartmentDto = mapper.map(this.departmentRepository.save(department), DepartmentDTO.class);

        return updatedDepartmentDto;
    }

    @Override
    public Page<DepartmentDTO> findAll(Pageable pageable) {

        Page<Department> departments = this.departmentRepository.findAllByActive(pageable, true);

        return departments.map(department -> mapper.map(department, DepartmentDTO.class));
    }

    @Transactional
    @Override
    public void delete(long id) {

        Department department = this.getActiveDepartmentById(id);

        this.deleteBusinessLogic(department);

        departmentRepository.save(department);
    }

    private void updatePayLoadFields(DepartmentDTO dto, Department department) {

        department.setName(dto.getName());
        department.setCode(dto.getCode().toUpperCase());
        department.setDescription(dto.getDescription());
        department.setUpdatedDate(LocalDateTime.now());
    }

    private void deleteBusinessLogic(Department department) {

        department.setActive(Boolean.FALSE);
        department.setDeleted(Boolean.TRUE);
    }

    private void saveBusinessLogic(Department department) {

        this.checkIfDepartmentExists(department);

        department.setId(null);
        department.setActive(true);
        department.setDeleted(false);
        department.setCreatedDate(LocalDateTime.now());
        department.setCode(department.getCode().toUpperCase());
    }

    private Department getActiveDepartmentByCode(String code) {

        return this.departmentRepository.findByCodeAndActive(code.toUpperCase(), true)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "code", code));
    }

    private Department getActiveDepartmentById(Long id) {

        return this.departmentRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", String.valueOf(id)));
    }

    private void checkIfDepartmentExists(Department department) throws ResourceAlreadyExistsException {

        String code = department.getCode();

        if (departmentRepository.existsByCode(code)) {
            throw new ResourceAlreadyExistsException("Department", "code", code);
        }

    }
}
