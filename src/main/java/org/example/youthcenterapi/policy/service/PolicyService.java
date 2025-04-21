package org.example.youthcenterapi.policy.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.youthcenterapi.policy.entity.Policy;
import org.example.youthcenterapi.policy.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final RestTemplate restTemplate;

    // API 키는 application-secret.yml 등에서 주입받습니다.
    @Value("${api.youth-policy-key}")
    private String apiKey;

    /**
     * OpenAPI를 호출하여 전체 청년정책 데이터를 페이지 단위로 가져오고,
     * 각 페이지마다 데이터를 DB에 저장한 후 DB 저장 진행 건수와 함께
     * SSE 이벤트("dbProgress")를 클라이언트에 전송합니다.
     * 또한, 주기적으로 "heartbeat" 이벤트를 전송하여 연결 유지에 도움을 줍니다.
     */
    public void fetchAndSavePoliciesWithProgress(SseEmitter emitter) throws Exception {
        int pageNum = 1;
        int pageSize = 100;    // 필요에 따라 조정 가능(예: 200 등)
        boolean moreData = true;
        int totalStored = 0;
        ObjectMapper mapper = new ObjectMapper();

        // 하트비트 전송 주기를 밀리초 단위로 지정(예: 30초)
        long heartbeatInterval = 30_000L;
        long lastHeartbeat = System.currentTimeMillis();

        while (moreData) {
            // OpenAPI URL 구성 (필수 파라미터 포함: apiKeyNm, pageNum, pageSize, rtnType=json)
            String url = "https://www.youthcenter.go.kr/go/ythip/getPlcy?apiKeyNm="
                    + apiKey + "&pageNum=" + pageNum
                    + "&pageSize=" + pageSize
                    + "&rtnType=json";
            try {
                String response = restTemplate.getForObject(url, String.class);
                JsonNode root = mapper.readTree(response);
                int resultCode = root.path("resultCode").asInt();
                if (resultCode != 200) {
                    emitter.send(SseEmitter.event().name("error")
                            .data("API call failed with resultCode: " + resultCode));
                    break;
                }
                JsonNode resultNode = root.path("result");
                JsonNode policyListNode = resultNode.path("youthPolicyList");

                if (policyListNode == null || !policyListNode.isArray() || policyListNode.size() == 0) {
                    moreData = false;
                    break;
                } else {
                    // 각 정책을 엔티티로 변환하고, 페이지 단위로 DB 저장
                    List<Policy> pagePolicies = new ArrayList<>();
                    for (JsonNode node : policyListNode) {
                        Policy policy = mapper.treeToValue(node, Policy.class);
                        pagePolicies.add(policy);
                    }
                    policyRepository.saveAll(pagePolicies);
                    totalStored += pagePolicies.size();

                    // DB 저장 진행 메시지를 SSE로 전송
                    emitter.send(SseEmitter.event().name("dbProgress")
                            .data("DB 저장 진행: " + totalStored + " 건 저장 완료 (페이지: " + pageNum + ")"));

                    // 주기적 하트비트 전송 (연결 유지를 위해)
                    long now = System.currentTimeMillis();
                    if (now - lastHeartbeat > heartbeatInterval) {
                        emitter.send(SseEmitter.event().name("heartbeat").data("keep-alive"));
                        lastHeartbeat = now;
                    }

                    // 반환된 데이터 건수가 pageSize보다 작으면 마지막 페이지로 간주
                    if (policyListNode.size() < pageSize) {
                        moreData = false;
                    } else {
                        pageNum++;
                    }
                }
            } catch (Exception e) {
                emitter.send(SseEmitter.event().name("error")
                        .data("Error on page " + pageNum + ": " + (e.getMessage() != null ? e.getMessage() : "Unknown error")));
                moreData = false;
            }
        }
        emitter.send(SseEmitter.event().name("complete")
                .data("전체 " + totalStored + " 건 DB 저장 완료."));
    }
}
