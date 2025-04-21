package org.example.youthcenterapi.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 페이징 정보를 매핑하기 위한 DTO
 * - totCount: 전체 건수
 * - pageNum: 현재 페이지 번호
 * - pageSize: 한 페이지당 조회 수
 */
@Data
public class PagingDto {
    @JsonProperty("totCount")
    private int totCount;

    @JsonProperty("pageNum")
    private int pageNum;

    @JsonProperty("pageSize")
    private int pageSize;
}
