package org.example.youthcenterapi.policyCode.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 청년정책코드 API 개별 코드 매핑 DTO
 * - 코드 그룹 및 상세 코드 정보
 */
@Data
public class PolicyCodeResponseDto {
    @JsonProperty("cdGroupCd")
    private String cdGroupCd;

    @JsonProperty("cdGroupNm")
    private String cdGroupNm;

    @JsonProperty("comCd")
    private String comCd;

    @JsonProperty("cdNm")
    private String cdNm;

    @JsonProperty("cdExpln")
    private String cdExpln;

    @JsonProperty("frstRegDt")
    private String frstRegDt;

    @JsonProperty("lastMdfcnDt")
    private String lastMdfcnDt;
}
