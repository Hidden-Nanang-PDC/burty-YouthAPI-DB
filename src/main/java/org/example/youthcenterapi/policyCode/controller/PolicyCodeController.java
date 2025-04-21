package org.example.youthcenterapi.policyCode.controller;

import org.example.youthcenterapi.policyCode.service.PolicyCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 청년정책코드 저장 작업을 트리거하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class PolicyCodeController {

    private final PolicyCodeService codeService;

    /**
     * 정책코드 저장 요청 처리 SSE 엔드포인트
     */
    @GetMapping("/fetch-policy-codes-stream")
    public SseEmitter fetchPolicyCodes() {
        return codeService.fetchAndSavePolicyCodes();
    }
}
