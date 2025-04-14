// 개별 정책 API 응답 DTO
package org.example.youthcenterapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PolicyResponseDto {

    @JsonProperty("plcyNo")
    private String plcyNo;

    @JsonProperty("plcyNm")
    private String plcyNm;

    @JsonProperty("plcyExplnCn")
    private String plcyExplnCn;

    @JsonProperty("plcyKywdNm")
    private String plcyKywdNm;

    // 기타 필요한 필드 선언
}
