package org.example.youthcenterapi.service;

import java.util.ArrayList;
import java.util.List;

import org.example.youthcenterapi.model.entity.Policy;
import org.example.youthcenterapi.model.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final RestTemplate restTemplate;

    // application-secret.yml 또는 다른 설정파일에서 API 키를 읽어올 수 있음
    @Value("${api.youth-policy-key}")
    private String apiKey;

    public void fetchAndSavePolicies() {
        // OpenAPI 기본 URL 및 파라미터 준비 (필요한 파라미터 추가)
        String url = "https://www.youthcenter.go.kr/go/ythip/getPlcy?" +
                "apiKeyNm=" + apiKey +
                "&pageNum=1" +
                "&pageSize=100" +
                "&rtnType=json"; // 기타 필요한 파라미터 추가

        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            // API 문서에 따른 결과 코드와 메시지 체크 (예제에서는 result 하위의 youthPolicyList)
            JsonNode policyListNode = root.path("result").path("youthPolicyList");

            if (policyListNode.isArray()) {
                List<Policy> policies = new ArrayList<>();
                for (JsonNode node : policyListNode) {
                    // JSON 노드를 Policy 엔티티로 매핑
                    Policy policy = mapper.treeToValue(node, Policy.class);
                    policies.add(policy);
                }
                policyRepository.saveAll(policies);
                System.out.println("총 " + policies.size() + " 건의 정책 정보 저장 완료.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 예외처리 추가: 로깅, 알림 등
        }
    }
}
