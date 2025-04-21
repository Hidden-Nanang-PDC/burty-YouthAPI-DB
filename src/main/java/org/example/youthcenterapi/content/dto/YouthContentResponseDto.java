package org.example.youthcenterapi.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 청년콘텐츠 API 개별 아이템 매핑 DTO
 * - API에서 반환하는 게시물 한 건의 필드를 모두 포함
 */
@Data
public class YouthContentResponseDto {
    @JsonProperty("bbsSn")
    private String bbsSn;

    @JsonProperty("pstSn")
    private String pstSn;

    @JsonProperty("pstSeSn")
    private String pstSeSn;

    @JsonProperty("pstSeNm")
    private String pstSeNm;

    @JsonProperty("pstTtl")
    private String pstTtl;

    @JsonProperty("pstWholCn")
    private String pstWholCn;

    @JsonProperty("pstUrlAddr")
    private String pstUrlAddr;

    @JsonProperty("atchFile")
    private String atchFile;

    @JsonProperty("thumnamilYn")
    private String thumnamilYn;

    @JsonProperty("frstRgtrNm")
    private String frstRgtrNm;

    @JsonProperty("frstRegDt")
    private String frstRegDt;

    @JsonProperty("lastMdfrNm")
    private String lastMdfrNm;

    @JsonProperty("lastMdfcnDt")
    private String lastMdfcnDt;

    @JsonProperty("pstInqCnt")
    private String pstInqCnt;
}
