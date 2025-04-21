package org.example.youthcenterapi.content.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.youthcenterapi.content.entity.YouthContent;

/**
 * YouthContent 엔티티의 CRUD 처리를 담당하는 JPA Repository
 */
public interface YouthContentRepository extends JpaRepository<YouthContent, String> {
}
