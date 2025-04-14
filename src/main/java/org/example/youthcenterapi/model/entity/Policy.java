// DB 저장용 Entity
package org.example.youthcenterapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
@Table(name = "policy")
public class Policy {

    @Id
    @Column(name = "plcy_no", nullable = false, unique = true)
    private String plcyNo;

    @Column(name = "plcy_nm")
    private String plcyNm;

    @Column(name = "plcy_expln_cn", length = 2000)
    private String plcyExplnCn;

    @Column(name = "plcy_kywd_nm")
    private String plcyKywdNm;

    // 필요한 추가 필드는 계속 확장할 수 있음
}
