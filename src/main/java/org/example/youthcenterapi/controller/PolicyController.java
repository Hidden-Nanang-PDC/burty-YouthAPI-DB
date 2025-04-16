package org.example.youthcenterapi.controller;

import org.example.youthcenterapi.service.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @GetMapping("/fetch-policies")
    public ResponseEntity<String> fetchPolicies() {
        policyService.fetchAndSavePolicies();
        return ResponseEntity.ok("정책 데이터 저장 요청 완료");
    }
}
