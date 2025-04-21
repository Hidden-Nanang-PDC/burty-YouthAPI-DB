package org.example.youthcenterapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.youthcenterapi.model.dto.PolicyCodeListResponseDto;
import org.example.youthcenterapi.model.dto.PolicyCodeResponseDto;
import org.example.youthcenterapi.model.entity.PolicyCode;
import org.example.youthcenterapi.model.repository.PolicyCodeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 청년정책코드 API를 호출하여 데이터를 파싱하고
 * DB에 저장하는 서비스
 */
@Service
@RequiredArgsConstructor
public class PolicyCodeService {

    @Value("${api.youth-policy-code-key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final PolicyCodeRepository repository;

    /**
     * 코드를 한 번에 모두 조회 → DB 저장 → 완료 이벤트 전송
     */
    public SseEmitter fetchAndSavePolicyCodes() {
        SseEmitter emitter = new SseEmitter(10 * 60_000L);

        CompletableFuture.runAsync(() -> {
            try {
                // 1. API 호출
                String url = String.format(
                        "https://www.youthcenter.go.kr/go/ythip/getPolicyCode?apiKeyNm=%s&rtnType=JSON",
                        apiKey
                );
                String resp = restTemplate.getForObject(url, String.class);

                // 2. JSON 파싱
                PolicyCodeListResponseDto listDto =
                        objectMapper.readValue(resp, PolicyCodeListResponseDto.class);
                List<PolicyCodeResponseDto> codes = listDto.getResult().getYouthPolicyList();

                // 3. DTO → Entity 저장
                int total = 0;
                for (PolicyCodeResponseDto dto : codes) {
                    PolicyCode entity = new PolicyCode(
                            dto.getComCd(),
                            dto.getCdGroupCd(),
                            dto.getCdGroupNm(),
                            dto.getCdNm(),
                            dto.getCdExpln(),
                            dto.getFrstRegDt(),
                            dto.getLastMdfcnDt()
                    );
                    repository.save(entity);
                    total++;
                    emitter.send(SseEmitter.event()
                            .name("progress")
                            .data("저장 중: " + total + " / " + codes.size() + "건"));
                }

                // 4. 완료
                emitter.send(SseEmitter.event()
                        .name("complete")
                        .data("총 " + total + "건 저장 완료."));
                emitter.complete();

            } catch (Exception e) {
                try { emitter.send(SseEmitter.event().name("error").data(e.getMessage())); }
                catch (Exception ignored) {}
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }
}
