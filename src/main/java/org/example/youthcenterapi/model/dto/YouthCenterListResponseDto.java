package org.example.youthcenterapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * 청년공간 API 전체 응답 매핑 DTO
 * - resultCode, resultMessage
 * - result 내부에 PagingDto와 YouthCenterResponseDto 리스트 포함
 */
@Data
public class YouthCenterListResponseDto {
    private int resultCode;
    private String resultMessage;

    @JsonProperty("result")
    private YouthCenterResult result;

    @Data
    public static class YouthCenterResult {
        @JsonProperty("pagging")
        private PagingDto pagging;

        @JsonProperty("youthPolicyList")
        private List<YouthCenterResponseDto> youthPolicyList;
    }
}
