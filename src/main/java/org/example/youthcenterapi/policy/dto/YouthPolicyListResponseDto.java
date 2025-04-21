// 전체 응답 (wrapper)
package org.example.youthcenterapi.policy.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YouthPolicyListResponseDto {

    @JsonProperty("result")
    private YouthPolicyResult result;

    private int resultCode;
    private String resultMessage;

    @Data
    public static class YouthPolicyResult {
        @JsonProperty("youthPolicyList")
        private List<PolicyResponseDto> youthPolicyList;
    }
}
