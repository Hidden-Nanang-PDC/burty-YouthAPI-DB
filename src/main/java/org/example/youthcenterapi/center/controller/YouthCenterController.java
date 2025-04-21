package org.example.youthcenterapi.center.controller;

import org.example.youthcenterapi.center.service.YouthCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 청년공간 저장 작업을 트리거하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class YouthCenterController {

    private final YouthCenterService centerService;

    /**
     * 공간 정보 저장 요청 처리 SSE 엔드포인트
     * @param pageSize 페이지 사이즈 (기본: 50)
     * @return SseEmitter
     */
    @GetMapping("/fetch-centers-stream")
    public SseEmitter fetchCenters(@RequestParam(defaultValue = "50") int pageSize) {
        return centerService.fetchAndSaveCenters(pageSize);
    }
}
