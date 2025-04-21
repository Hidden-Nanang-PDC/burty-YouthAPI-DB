package org.example.youthcenterapi.content.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.youthcenterapi.content.dto.YouthContentListResponseDto;
import org.example.youthcenterapi.content.dto.YouthContentResponseDto;
import org.example.youthcenterapi.content.entity.YouthContent;
import org.example.youthcenterapi.content.repository.YouthContentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 청년콘텐츠 API를 호출하여 데이터를 파싱하고
 * DB에 저장하는 서비스
 * - SseEmitter로 진행상황 스트리밍
 */
@Service
@RequiredArgsConstructor
public class YouthContentService {

    @Value("${api.youth-content-key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final YouthContentRepository repository;

    /**
     * 페이지 단위로 데이터를 조회 → DB 저장 → 진행상황 전송
     * @param pageSize 한 페이지 당 조회 건수
     * @return SseEmitter
     */
    public SseEmitter fetchAndSaveContents(int pageSize) {
        SseEmitter emitter = new SseEmitter(10 * 60_000L);
        CompletableFuture.runAsync(() -> {
            int pageNum = 1;
            int totalStored = 0;
            boolean moreData = true;

            while (moreData) {
                try {
                    String url = String.format(
                            "https://www.youthcenter.go.kr/go/ythip/getContent?apiKeyNm=%s&pageNum=%d&pageSize=%d&rtnType=JSON",
                            apiKey, pageNum, pageSize
                    );
                    String resp = restTemplate.getForObject(url, String.class);

                    YouthContentListResponseDto listDto =
                            objectMapper.readValue(resp, YouthContentListResponseDto.class);
                    List<YouthContentResponseDto> items = listDto.getResult().getYouthPolicyList();

                    if (items == null || items.isEmpty()) break;

                    // DTO → Entity 변환 및 저장
                    for (YouthContentResponseDto dto : items) {
                        YouthContent entity = new YouthContent(
                                dto.getPstSn(), dto.getBbsSn(), dto.getPstSeSn(), dto.getPstSeNm(),
                                dto.getPstTtl(), dto.getPstWholCn(), dto.getPstUrlAddr(),
                                dto.getAtchFile(), dto.getThumnamilYn(), dto.getFrstRgtrNm(),
                                dto.getFrstRegDt(), dto.getLastMdfrNm(), dto.getLastMdfcnDt(),
                                dto.getPstInqCnt()
                        );
                        repository.save(entity);
                        totalStored++;
                    }

                    emitter.send(SseEmitter.event()
                            .name("progress")
                            .data("페이지 " + pageNum + " 저장: " + items.size() + "건"));
                    pageNum++;
                    if (items.size() < pageSize) moreData = false;

                } catch (Exception e) {
                    try { emitter.send(SseEmitter.event().name("error").data(e.getMessage())); }
                    catch (Exception ignored) {}
                    break;
                }
            }

            try {
                emitter.send(SseEmitter.event()
                        .name("complete")
                        .data("총 " + totalStored + "건 저장 완료."));
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
}
