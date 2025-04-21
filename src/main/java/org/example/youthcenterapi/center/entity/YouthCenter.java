package org.example.youthcenterapi.center.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DB 테이블 youth_center 매핑 엔티티
 * - 텍스트가 긴 필드는 TEXT/LONGTEXT로 저장
 */
@Entity
@Table(name = "youth_center")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YouthCenter {
    @Id
    @Column(name = "cntr_sn", length = 50)
    private String cntrSn;

    @Column(name = "cntr_nm")
    private String cntrNm;

    @Column(name = "oper_inst_nm")
    private String operInstNm;

    @Column(name = "cntr_oper_hr_cn", columnDefinition = "TEXT")
    private String cntrOperHrCn;

    @Column(name = "cntr_utztn_hr_cn", columnDefinition = "TEXT")
    private String cntrUtztnHrCn;

    @Column(name = "cntr_addr")
    private String cntrAddr;

    @Column(name = "cntr_daddr")
    private String cntrDaddr;

    @Column(name = "cntr_telno")
    private String cntrTelno;

    @Column(name = "sbsd_cst_cd")
    private String sbsdCstCd;

    @Column(name = "pic_flnm")
    private String picFlnm;

    @Column(name = "pic_telno")
    private String picTelno;

    @Column(name = "pic_eml_addr")
    private String picEmlAddr;

    @Column(name = "cntr_url_addr", columnDefinition = "TEXT")
    private String cntrUrlAddr;

    @Column(name = "vdo_itvw_psblty_yn")
    private String vdoItvwPsbltyYn;

    @Column(name = "vdo_itvw_plc_cnt")
    private String vdoItvwPlcCnt;

    @Column(name = "empm_dscsn_psblty_cd")
    private String empmDscsnPsbltyCd;

    @Column(name = "cntr_gd_cn", columnDefinition = "TEXT")
    private String cntrGdCn;

    @Column(name = "pjtr_cnt")
    private String pjtrCnt;

    @Column(name = "pc_cnt")
    private String pcCnt;

    @Column(name = "prt_cnt")
    private String prtCnt;

    @Column(name = "mic_cnt")
    private String micCnt;

    @Column(name = "sbsd_fclt_etc_cn")
    private String sbsdFcltEtcCn;

    @Column(name = "stdg_ctpv_cd")
    private String stdgCtpvCd;

    @Column(name = "stdg_ctpv_cd_nm")
    private String stdgCtpvCdNm;

    @Column(name = "stdg_sgg_cd")
    private String stdgSggCd;

    @Column(name = "stdg_sgg_cd_nm")
    private String stdgSggCdNm;

    @Column(name = "rsvt_url_addr")
    private String rsvtUrlAddr;

    @Column(name = "frst_rgtr_nm")
    private String frstRgtrNm;

    @Column(name = "frst_reg_dt")
    private String frstRegDt;

    @Column(name = "last_mdfr_nm")
    private String lastMdfrNm;

    @Column(name = "last_mdfcn_dt")
    private String lastMdfcnDt;

    @Column(name = "exs_file_nm")
    private String exsFileNm;

    @Column(name = "atch_file_sz")
    private String atchFileSz;

    @Lob
    @Column(name = "atch_file", columnDefinition = "LONGTEXT")
    private String atchFile;

    @Column(name = "atch_file_extn_nm")
    private String atchFileExtnNm;

    @Column(name = "cntr_plc_sn")
    private String cntrPlcSn;

    @Column(name = "tnumnail_yn")
    private String tnumnailYn;

    @Column(name = "cntr_plc_expln", columnDefinition = "TEXT")
    private String cntrPlcExpln;
}
