package org.example.youthcenterapi.center.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 청년공간 API 개별 아이템 매핑 DTO
 * - 공간(센터) 한 건의 정보를 매핑
 */
@Data
public class YouthCenterResponseDto {
    @JsonProperty("cntrSn")
    private String cntrSn;

    @JsonProperty("cntrNm")
    private String cntrNm;

    @JsonProperty("operInstNm")
    private String operInstNm;

    @JsonProperty("cntrOperHrCn")
    private String cntrOperHrCn;

    @JsonProperty("cntrUtztnHrCn")
    private String cntrUtztnHrCn;

    @JsonProperty("cntrAddr")
    private String cntrAddr;

    @JsonProperty("cntrDaddr")
    private String cntrDaddr;

    @JsonProperty("cntrTelno")
    private String cntrTelno;

    @JsonProperty("sbsdCstCd")
    private String sbsdCstCd;

    @JsonProperty("picFlnm")
    private String picFlnm;

    @JsonProperty("picTelno")
    private String picTelno;

    @JsonProperty("picEmlAddr")
    private String picEmlAddr;

    @JsonProperty("cntrUrlAddr")
    private String cntrUrlAddr;

    @JsonProperty("vdoItvwPsbltyYn")
    private String vdoItvwPsbltyYn;

    @JsonProperty("vdoItvwPlcCnt")
    private String vdoItvwPlcCnt;

    @JsonProperty("empmDscsnPsbltyCd")
    private String empmDscsnPsbltyCd;

    @JsonProperty("cntrGdCn")
    private String cntrGdCn;

    @JsonProperty("pjtrCnt")
    private String pjtrCnt;

    @JsonProperty("pcCnt")
    private String pcCnt;

    @JsonProperty("prtCnt")
    private String prtCnt;

    @JsonProperty("micCnt")
    private String micCnt;

    @JsonProperty("sbsdFcltEtcCn")
    private String sbsdFcltEtcCn;

    @JsonProperty("stdgCtpvCd")
    private String stdgCtpvCd;

    @JsonProperty("stdgCtpvCdNm")
    private String stdgCtpvCdNm;

    @JsonProperty("stdgSggCd")
    private String stdgSggCd;

    @JsonProperty("stdgSggCdNm")
    private String stdgSggCdNm;

    @JsonProperty("rsvt_url_addr")
    private String rsvtUrlAddr;

    @JsonProperty("frstRgtrNm")
    private String frstRgtrNm;

    @JsonProperty("frstRegDt")
    private String frstRegDt;

    @JsonProperty("lastMdfrNm")
    private String lastMdfrNm;

    @JsonProperty("lastMdfcnDt")
    private String lastMdfcnDt;

    @JsonProperty("exsFileNm")
    private String exsFileNm;

    @JsonProperty("atchFileSz")
    private String atchFileSz;

    @JsonProperty("atchFile")
    private String atchFile;

    @JsonProperty("atchFileExtnNm")
    private String atchFileExtnNm;

    @JsonProperty("cntrPlcSn")
    private String cntrPlcSn;

    @JsonProperty("tnumnailYn")
    private String tnumnailYn;

    @JsonProperty("cntrPlcExpln")
    private String cntrPlcExpln;
}
