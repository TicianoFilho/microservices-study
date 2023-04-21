package br.com.estudo.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String departmentCode;

    private String OrganizationCode;

    private boolean active;

    private boolean deleted;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
