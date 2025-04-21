// JPA Repository
package org.example.youthcenterapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.youthcenterapi.model.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, String> {
    // 기본적인 CRUD 메서드가 제공됨
}
