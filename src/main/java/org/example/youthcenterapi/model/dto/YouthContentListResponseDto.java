package org.example.youthcenterapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 청년콘텐츠 API 전체 응답 매핑 DTO
 * - resultCode, resultMessage
 * - result 내부에 PagingDto와 YouthContentResponseDto 리스트 포함
 */
@Data
public class YouthContentListResponseDto {
    private int resultCode;
    private String resultMessage;

    @JsonProperty("result")
    private YouthContentResult result;

    @Data
    public static class YouthContentResult {
        @JsonProperty("pagging")
        private PagingDto pagging;

        @JsonProperty("youthPolicyList")
        private List<YouthContentResponseDto> youthPolicyList;
    }
}
