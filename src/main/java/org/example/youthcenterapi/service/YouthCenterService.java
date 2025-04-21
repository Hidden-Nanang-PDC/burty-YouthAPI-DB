package org.example.youthcenterapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.youthcenterapi.model.dto.PagingDto;
import org.example.youthcenterapi.model.dto.YouthCenterListResponseDto;
import org.example.youthcenterapi.model.dto.YouthCenterResponseDto;
import org.example.youthcenterapi.model.entity.YouthCenter;
import org.example.youthcenterapi.model.repository.YouthCenterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 청년공간 API를 호출하여 데이터를 파싱하고
 * DB에 저장하는 서비스
 * - 첫 호출에서 전체 페이지 수 계산
 * - totalPages 만큼만 반복 요청
 */
@Service
@RequiredArgsConstructor
public class YouthCenterService {

    @Value("${api.youth-center-key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final YouthCenterRepository centerRepository;

    public SseEmitter fetchAndSaveCenters(int pageSize) {
        SseEmitter emitter = new SseEmitter(10 * 60_000L);
        CompletableFuture.runAsync(() -> {
            try {
                // 첫 페이지 호출
                String firstUrl = buildUrl(1, pageSize);
                String firstResp = restTemplate.getForObject(firstUrl, String.class);
                YouthCenterListResponseDto firstDto =
                        objectMapper.readValue(firstResp, YouthCenterListResponseDto.class);

                // 전체 페이지 수 계산
                PagingDto paging = firstDto.getResult().getPagging();
                int totalPages = (paging.getTotCount() + pageSize - 1) / pageSize;
                int totalStored = 0;

                // 첫 페이지 데이터 처리
                List<YouthCenterResponseDto> firstItems = firstDto.getResult().getYouthPolicyList();
                for (YouthCenterResponseDto dto : firstItems) {
                    centerRepository.save(toEntity(dto));
                    totalStored++;
                }
                emitter.send(SseEmitter.event()
                        .name("progress")
                        .data("페이지 1 저장: " + firstItems.size() + "건"));

                // 2페이지부터 마지막까지 순차 처리
                for (int pageNum = 2; pageNum <= totalPages; pageNum++) {
                    String url = buildUrl(pageNum, pageSize);
                    String resp = restTemplate.getForObject(url, String.class);
                    YouthCenterListResponseDto dtoList =
                            objectMapper.readValue(resp, YouthCenterListResponseDto.class);

                    List<YouthCenterResponseDto> items = dtoList.getResult().getYouthPolicyList();
                    if (items == null || items.isEmpty()) break;

                    for (YouthCenterResponseDto dto : items) {
                        centerRepository.save(toEntity(dto));
                        totalStored++;
                    }
                    emitter.send(SseEmitter.event()
                            .name("progress")
                            .data("페이지 " + pageNum + " 저장: " + items.size() + "건"));
                }

                // 완료 이벤트 전송
                emitter.send(SseEmitter.event()
                        .name("complete")
                        .data("총 " + totalStored + "건 저장 완료."));
                emitter.complete();

            } catch (Exception e) {
                try { emitter.send(SseEmitter.event().name("error").data(e.getMessage())); }
                catch (Exception ignored) {}
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    private String buildUrl(int pageNum, int pageSize) {
        return String.format(
                "https://www.youthcenter.go.kr/go/ythip/getSpace?apiKeyNm=%s&pageNum=%d&pageSize=%d&rtnType=JSON",
                apiKey, pageNum, pageSize
        );
    }

    private YouthCenter toEntity(YouthCenterResponseDto dto) {
        return new YouthCenter(
                dto.getCntrSn(),
                dto.getCntrNm(),
                dto.getOperInstNm(),
                dto.getCntrOperHrCn(),
                dto.getCntrUtztnHrCn(),
                dto.getCntrAddr(),
                dto.getCntrDaddr(),
                dto.getCntrTelno(),
                dto.getSbsdCstCd(),
                dto.getPicFlnm(),
                dto.getPicTelno(),
                dto.getPicEmlAddr(),
                dto.getCntrUrlAddr(),
                dto.getVdoItvwPsbltyYn(),
                dto.getVdoItvwPlcCnt(),
                dto.getEmpmDscsnPsbltyCd(),
                dto.getCntrGdCn(),
                dto.getPjtrCnt(),
                dto.getPcCnt(),
                dto.getPrtCnt(),
                dto.getMicCnt(),
                dto.getSbsdFcltEtcCn(),
                dto.getStdgCtpvCd(),
                dto.getStdgCtpvCdNm(),
                dto.getStdgSggCd(),
                dto.getStdgSggCdNm(),
                dto.getRsvtUrlAddr(),
                dto.getFrstRgtrNm(),
                dto.getFrstRegDt(),
                dto.getLastMdfrNm(),
                dto.getLastMdfcnDt(),
                dto.getExsFileNm(),
                dto.getAtchFileSz(),
                dto.getAtchFile(),
                dto.getAtchFileExtnNm(),
                dto.getCntrPlcSn(),
                dto.getTnumnailYn(),
                dto.getCntrPlcExpln()
        );
    }
}
