package org.example.youthcenterapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DB 테이블 youth_policy_code 매핑 엔티티
 * - comCd(공통코드)를 기본키로 사용
 */
@Entity
@Table(name = "youth_policy_code")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyCode {
    @Id
    @Column(name = "com_cd", length = 50)
    private String comCd;

    @Column(name = "cd_group_cd")
    private String cdGroupCd;

    @Column(name = "cd_group_nm")
    private String cdGroupNm;

    @Column(name = "cd_nm")
    private String cdNm;

    @Column(name = "cd_expln", columnDefinition = "TEXT")
    private String cdExpln;

    @Column(name = "frst_reg_dt")
    private String frstRegDt;

    @Column(name = "last_mdfcn_dt")
    private String lastMdfcnDt;
}
