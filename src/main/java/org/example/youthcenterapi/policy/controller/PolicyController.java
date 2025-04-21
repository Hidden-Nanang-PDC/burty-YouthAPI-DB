package org.example.youthcenterapi.policy.controller;

import org.example.youthcenterapi.policy.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    // 타임아웃 10분으로 SseEmitter 생성
    @GetMapping("/fetch-policies-stream")
    public SseEmitter streamFetchPolicies() {
        SseEmitter emitter = new SseEmitter(600_000L);
        CompletableFuture.runAsync(() -> {
            try {
                policyService.fetchAndSavePoliciesWithProgress(emitter);
                emitter.complete();
            } catch (Exception e) {
                try {
                    emitter.send(SseEmitter.event().name("error")
                            .data("Error: " + (e.getMessage() != null ? e.getMessage() : "Unknown error")));
                } catch (Exception ex) {
                    // 무시
                }
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
}
