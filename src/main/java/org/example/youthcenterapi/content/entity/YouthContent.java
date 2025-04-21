package org.example.youthcenterapi.content.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DB 테이블 youth_content 매핑 엔티티
 * - atchFile: Base64 문자열을 LONGTEXT 컬럼으로 저장
 */
@Entity
@Table(name = "youth_content")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YouthContent {
    @Id
    @Column(name = "pst_sn", length = 50)
    private String pstSn;

    @Column(name = "bbs_sn")
    private String bbsSn;

    @Column(name = "pst_se_sn")
    private String pstSeSn;

    @Column(name = "pst_se_nm")
    private String pstSeNm;

    @Column(name = "pst_ttl", columnDefinition = "TEXT")
    private String pstTtl;

    @Column(name = "pst_whol_cn", columnDefinition = "TEXT")
    private String pstWholCn;

    @Column(name = "pst_url_addr", columnDefinition = "TEXT")
    private String pstUrlAddr;

    @Lob
    @Column(name = "atch_file", columnDefinition = "LONGTEXT")
    private String atchFile;

    @Column(name = "thumnamil_yn")
    private String thumnamilYn;

    @Column(name = "frst_rgtr_nm")
    private String frstRgtrNm;

    @Column(name = "frst_reg_dt")
    private String frstRegDt;

    @Column(name = "last_mdfr_nm")
    private String lastMdfrNm;

    @Column(name = "last_mdfcn_dt")
    private String lastMdfcnDt;

    @Column(name = "pst_inq_cnt")
    private String pstInqCnt;
}
