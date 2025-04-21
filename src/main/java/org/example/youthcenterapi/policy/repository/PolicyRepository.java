// JPA Repository
package org.example.youthcenterapi.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.youthcenterapi.policy.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, String> {
    // 기본적인 CRUD 메서드가 제공됨
}
