package org.example.youthcenterapi.policyCode.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * 청년정책코드 API 전체 응답 매핑 DTO
 * - resultCode, resultMessage
 * - result 내부에 PolicyCodeResponseDto 리스트 포함
 */
@Data
public class PolicyCodeListResponseDto {
    private int resultCode;
    private String resultMessage;

    @JsonProperty("result")
    private PolicyCodeResult result;

    @Data
    public static class PolicyCodeResult {
        @JsonProperty("youthPolicyList")
        private List<PolicyCodeResponseDto> youthPolicyList;
    }
}
