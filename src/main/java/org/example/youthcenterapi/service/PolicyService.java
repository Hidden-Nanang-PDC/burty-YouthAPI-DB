package org.example.youthcenterapi.service;

import org.example.youthcenterapi.model.dto.YouthPolicyListResponseDto;
import org.example.youthcenterapi.model.dto.PolicyResponseDto;
import org.example.youthcenterapi.model.entity.Policy;
import org.example.youthcenterapi.model.repository.PolicyRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PolicyService {

    private final RestTemplate restTemplate;
    private final PolicyRepository policyRepository;
    private final ObjectMapper objectMapper;

    @Value("${youth.api.url:https://www.youthcenter.go.kr/go/ythip/getPlcy}")
    private String apiUrl;

    @Value("${YOUTH_API_KEY}")
    private String apiKey;

    public PolicyService(RestTemplate restTemplate, PolicyRepository policyRepository, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.policyRepository = policyRepository;
        this.objectMapper = objectMapper;
    }

    public void fetchAndSavePolicies() {
        // 예시: pageNum=1, pageSize=10, rtnType=json, pageType=1 (목록 조회)
        String url = apiUrl + "?apiKeyNm=" + apiKey + "&rtnType=json&pageNum=1&pageSize=10&pageType=1";
        try {
            String responseString = restTemplate.getForObject(url, String.class);
            // JSON 응답을 파싱하여 YouthPolicyListResponseDto로 변환
            YouthPolicyListResponseDto responseDto = objectMapper.readValue(responseString, YouthPolicyListResponseDto.class);
            if(responseDto.getResult() != null && responseDto.getResult().getYouthPolicyList() != null) {
                List<PolicyResponseDto> policyDtos = responseDto.getResult().getYouthPolicyList();
                // DTO를 Entity로 변환 후 DB에 저장
                for (PolicyResponseDto dto : policyDtos) {
                    Policy policy = dtoToEntity(dto);
                    policyRepository.save(policy);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Policy dtoToEntity(PolicyResponseDto dto) {
        Policy policy = new Policy();
        policy.setPlcyNo(dto.getPlcyNo());
        policy.setPlcyNm(dto.getPlcyNm());
        policy.setPlcyExplnCn(dto.getPlcyExplnCn());
        policy.setPlcyKywdNm(dto.getPlcyKywdNm());
        return policy;
    }
}
