package org.example.youthcenterapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.youthcenterapi.model.entity.YouthContent;

/**
 * YouthContent 엔티티의 CRUD 처리를 담당하는 JPA Repository
 */
public interface YouthContentRepository extends JpaRepository<YouthContent, String> {
}
