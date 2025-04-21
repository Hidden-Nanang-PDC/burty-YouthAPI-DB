package org.example.youthcenterapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.youthcenterapi.model.entity.PolicyCode;

/**
 * PolicyCode 엔티티의 CRUD 처리를 담당하는 JPA Repository
 */
public interface PolicyCodeRepository extends JpaRepository<PolicyCode, String> {
}
