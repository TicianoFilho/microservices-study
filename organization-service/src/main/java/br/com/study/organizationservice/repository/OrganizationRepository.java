package br.com.study.organizationservice.repository;

import br.com.study.organizationservice.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
