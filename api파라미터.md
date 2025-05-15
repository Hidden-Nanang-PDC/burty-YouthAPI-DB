# youthcenterAPI_test

## 내가 쓴 데이터 목록만 정리

## 1. 정책 API 사용 필드

| 필드명               | 설명                                     |
| ----------------- | -------------------------------------- |
| `plcyNo`          | 정책번호 (상세조회 ID)                         |
| `mclsfNm`         | 정책 중분류명                                |
| `plcyNm`          | 정책명                                    |
| `plcyExplnCn`     | 정책 설명 (간략 텍스트)                         |
| `lclsfNm`         | 정책 대분류명                                |
| `sprvsnInstCdNm`  | 주관기관 코드명                               |
| `sprtTrgtMinAge`  | 지원대상 최소 연령                             |
| `sprtTrgtMaxAge`  | 지원대상 최대 연령                             |
| `aplyYmd`         | 신청기간 (예: "20250101\~20251231" 또는 "상시") |
| `plcyMajorCd`     | 정책전공요건코드                               |
| `jobCd`           | 정책취업요건코드                               |
| `schoolCd`        | 정책학력요건코드                               |
| `sBizCd`          | 정책특화요건코드                               |
| `plcyPvsnMthdCd`  | 정책제공방법코드                               |
| `plcyAprvSttsCd`  | 정책승인상태코드                               |
| `earnCndSeCd`     | 소득조건구분코드                               |
| `mrgSttsCd`       | 결혼상태코드                                 |
| `pvsnInstGroupCd` | 제공기관그룹코드                               |
| `zipCd`           | 정책거주지역코드                               |

---

## 2. 청년 콘텐츠 API 사용 필드

| 필드명          | 설명               |
| ------------ | ---------------- |
| `pstSn`      | 게시물 일련번호         |
| `pstSeNm`    | 게시물 구분명          |
| `pstTtl`     | 게시물 제목           |
| `pstWholCn`  | 게시물 전체 내용        |
| `frstRegDt`  | 최초 등록 일시         |
| `lastMdfrNm` | 최종 수정자명          |
| `atchFile`   | Base64 인코딩된 첨부파일 |

---

## 3. 청년 센터 API 사용 필드

| 필드명             | 설명        |
| --------------- | --------- |
| `cntrSn`        | 센터 일련번호   |
| `stdgCtpvCd`    | 시/도 코드    |
| `stdgCtpvCdNm`  | 시/도 코드명   |
| `stdgSggCd`     | 시/군/구 코드  |
| `stdgSggCdNm`   | 시/군/구 코드명 |
| `cntrNm`        | 센터명       |
| `operInstNm`    | 운영기관명     |
| `cntrOperHrCn`  | 센터 운영시간   |
| `cntrUtztnHrCn` | 센터 이용시간   |
| `cntrAddr`      | 센터 주소     |
| `cntrDaddr`     | 센터 상세주소   |
| `cntrTelno`     | 센터 전화번호   |
| `picFlnm`       | 담당자 성명    |
| `picTelno`      | 담당자 전화번호  |
| `picEmlAddr`    | 담당자 이메일주소 |
| `cntrUrlAddr`   | 센터 URL    |
| `cntrGdCn`      | 센터 안내내용   |
| `sbsdFcltEtcCn` | 부대시설 기타내용 |
| `frstRegDt`     | 최초등록일시    |
| `lastMdfcnDt`   | 최종수정일시    |

---

## 4. 청년 정책 코드 API 사용 필드

| 필드명         | 설명     |
| ----------- | ------ |
| `cdGroupCd` | 코드그룹코드 |
| `comCd`     | 공통코드   |
| `cdNm`      | 코드명    |
| `cdExpln`   | 코드설명   |
| `cdGroupNm` | 코드그룹명  |

---

## 1. 청년정책 API
- **요청 URL:** https://www.youthcenter.go.kr/go/ythip/getPlcy

### 요청 Parameter
| 항목 | 타입 | 필수여부 | 설명 |
|------|------|---------|------|
| apiKeyNm | String | Y | 인증키 |
| pageNum | Number | N | 페이지번호 |
| pageSize | Number | N | 페이지 크기 |
| pageType | String | N | 화면유형 (1:목록, 2:상세) |
| plcyNo | String | N | 정책번호 |
| rtnType | String | N | 반환형식(xml/json) |
| plcyKywdNm | String | N | 정책키워드(쉼표 구분) |
| plcyExplnCn | String | N | 정책설명 |
| plcyNm | String | N | 정책명 |
| zipCd | String | N | 법정시군구코드(5자리) |
| lclsfNm | String | N | 정책대분류명(쉼표 구분) |
| mclsfNm | String | N | 정책중분류명(쉼표 구분) |

### 출력 결과
| 항목 | 타입 | 설명 |
|------|------|------|
| plcyNo | String | 정책번호 |
| bscPlanCycl | String | 기본계획차수 |
| bscPlanPlcyWayNo | String | 기본계획정책방향번호 |
| bscPlanFcsAsmtNo | String | 기본계획중점과제번호 |
| bscPlanAsmtNo | String | 기본계획과제번호 |
| pvsnInstGroupCd | String | 제공기관그룹코드 |
| plcyPvsnMthdCd | String | 정책제공방법코드 |
| plcyAprvSttsCd | String | 정책승인상태코드 |
| plcyNm | String | 정책명 |
| plcyKywdNm | String | 정책키워드명 |
| plcyExplnCn | String | 정책설명내용 |
| lclsfNm | String | 정책대분류명 |
| mclsfNm | String | 정책중분류명 |
| plcySprtCn | String | 정책지원내용 |
| sprvsnInstCd | String | 주관기관코드 |
| sprvsnInstCdNm | String | 주관기관코드명 |
| sprvsnInstPicNm | String | 주관기관담당자명 |
| operInstCd | String | 운영기관코드 |
| operInstCdNm | String | 운영기관코드명 |
| operInstPicNm | String | 운영기관담당자명 |
| sprtSclLmtYn | String | 지원규모제한여부 |
| aplyPrdSeCd | String | 신청기간구분코드 |
| bizPrdSeCd | String | 사업기간구분코드 |
| bizPrdBgngYmd | String | 사업기간시작일자 |
| bizPrdEndYmd | String | 사업기간종료일자 |
| bizPrdEtcCn | String | 사업기간기타내용 |
| plcyAplyMthdCn | String | 정책신청방법내용 |
| srngMthdCn | String | 심사방법내용 |
| aplyUrlAddr | String | 신청URL주소 |
| sbmsnDcmntCn | String | 제출서류내용 |
| etcMttrCn | String | 기타사항내용 |
| refUrlAddr1 | String | 참고URL주소1 |
| refUrlAddr2 | String | 참고URL주소2 |
| sprtSclCnt | String | 지원규모수 |
| sprtArvlSeqYn | String | 지원도착순서여부 |
| sprtTrgtMinAge | String | 지원대상최소연령 |
| sprtTrgtMaxAge | String | 지원대상최대연령 |
| sprtTrgtAgeLmtYn | String | 지원대상연령제한여부 |
| mrgSttsCd | String | 결혼상태코드 |
| earnCndSeCd | String | 소득조건구분코드 |
| earnMinAmt | String | 소득최소금액 |
| earnMaxAmt | String | 소득최대금액 |
| earnEtcCn | String | 소득기타내용 |
| addAplyQlfcCndCn | String | 추가신청자격조건내용 |
| ptcpPrpTrgtCn | String | 참여제안대상내용 |
| inqCnt | String | 조회수 |
| rgtrInstCd | String | 등록자기관코드 |
| rgtrInstCdNm | String | 등록자기관코드명 |
| rgtrUpInstCd | String | 등록자상위기관코드 |
| rgtrUpInstCdNm | String | 등록자상위기관코드명 |
| rgtrHghrkInstCd | String | 등록자최상위기관코드 |
| rgtrHghrkInstCdNm | String | 등록자최상위기관코드명 |
| zipCd | String | 정책거주지역코드 |
| plcyMajorCd | String | 정책전공요건코드 |
| jobCd | String | 정책취업요건코드 |
| schoolCd | String | 정책학력요건코드 |
| aplyYmd | String | 신청기간 |
| frstRegDt | String | 최초등록일시 |
| lastMdfcnDt | String | 최종수정일시 |
| sBizCd | String | 정책특화요건코드 |

---

## 2. 기본계획중점과제 API
- **요청 URL:** https://www.youthcenter.go.kr/go/ythip/getBscPlanFcsAsmt

### 요청 Parameter
| 항목 | 타입 | 필수여부 | 설명 |
|------|------|---------|------|
| apiKeyNm | String | Y | 인증키 |
| rtnType | String | Y | 반환형식(xml/json) |

### 출력 결과
| 항목 | 타입 | 설명 |
|------|------|------|
| bscPlanCycl | String | 기본계획차수 |
| bscPlanPlcyWayNo | String | 기본계획정책방향번호 |
| bscPlanFcsAsmtNo | String | 기본계획중점과제번호 |
| fcsAsmtNm | String | 중점과제명 |

---

## 3. 기본계획정책방향 API
- **요청 URL:** https://www.youthcenter.go.kr/go/ythip/getPolicyWay

### 요청 Parameter
| 항목 | 타입 | 필수여부 | 설명 |
|------|------|---------|------|
| apiKeyNm | String | Y | 인증키 |
| rtnType | String | Y | 반환형식(xml/json) |

### 출력 결과
| 항목 | 타입 | 설명 |
|------|------|------|
| bscPlanCycl | String | 기본계획차수 |
| bscPlanPlcyWayNo | String | 기본계획정책방향번호 |
| wayNm | String | 방향명 |

---

## 4. 청년콘텐츠 API
- **요청 URL:** https://www.youthcenter.go.kr/go/ythip/getContent

### 요청 Parameter
| 항목 | 타입 | 필수여부 | 설명 |
|------|------|---------|------|
| apiKeyNm | String | Y | 인증키 |
| pageNum | Number | N | 페이지번호 |
| pageSize | Number | N | 페이지 크기 |
| pageType | String | N | 화면유형 |
| pstSn | String | N | 게시물일련번호 |
| pstSeCd | String | N | 게시물구분코드 |
| rtnType | String | Y | 반환형식(xml/json) |

### 출력 결과
| 항목 | 타입 | 설명 |
|------|------|------|
| bbsSn | String | 게시판일련번호 |
| pstSn | String | 게시물일련번호 |
| pstSeSn | String | 게시물구분일련번호 |
| pstTtl | String | 게시물제목 |
| pstWholCn | String | 게시물전체내용 |
| pstUrlAddr | String | 게시물URL주소 |
| atchFile | String | 첨부파일 |
| thumnamilYn | String | 썸네일여부 |
| pstSeNm | String | 게시물구분명 |
| frstRgtrNm | String | 최초등록자명 |
| frstRegDt | String | 최초등록일시 |
| lastMdfrNm | String | 최종수정자명 |
| lastMdfcnDt | String | 최종수정일시 |
| pstInqCnt | String | 게시물조회수 |

---

## 5. 청년센터 API
- **요청 URL:** https://www.youthcenter.go.kr/go/ythip/getSpace

### 요청 Parameter
| 항목 | 타입 | 필수여부 | 설명 |
|------|------|---------|------|
| apiKeyNm | String | Y | 인증키 |
| pageNum | Number | N | 페이지번호 |
| pageSize | Number | N | 페이지 크기 |
| pageType | Number | N | 화면유형 |
| ctpvCd | String | N | 시도코드 |
| sggCd | String | N | 시군구코드 |
| plcSn | String | N | 센터일련번호 |
| plcType | String | N | 센터유형 |
| rtnType | String | Y | 반환형식(xml/json) |

### 출력 결과
| 항목 | 타입 | 설명 |
|------|------|------|
| cntrSn | String | 센터일련번호 |
| cntrNm | String | 센터명 |
| operInstNm | String | 운영기관명 |
| cntrOperHrCn | String | 운영시간 |
| cntrUtztnHrCn | String | 이용시간 |
| cntrAddr | String | 주소 |
| cntrDaddr | String | 상세주소 |
| cntrTelno | String | 전화번호 |
| sbsdCstCd | String | 부대비용코드 |
| picFlnm | String | 담당자 성명 |
| picTelno | String | 담당자 전화번호 |
| picEmlAddr | String | 담당자 이메일 |
| cntrUrlAddr | String | 센터URL |
| vdoItvwPsbltyYn | String | 화상면담 가능 여부 |
| vdoItvwPlcCnt | String | 화상면담 장소수 |
| empmDscsnPsbltyCd | String | 취업상담 가능 여부 코드 |
| cntrGdCn | String | 센터안내내용 |
| pjtrCnt | String | 프로젝터 수 |
| pcCnt | String | 컴퓨터 수 |
| prtCnt | String | 프린터 수 |
| micCnt | String | 마이크 수 |
| sbsdFcltEtcCn | String | 부대시설 기타내용 |
| stdgCtpvCd | String | 법정동 시도코드 |
| stdgCtpvCdNm | String | 법정동 시도코드명 |
| stdgSggCd | String | 법정동 시군구코드 |
| stdgSggCdNm | String | 법정동 시군구코드명 |
| rsvtUrlAddr | String | 예약 URL |
| frstRgtrNm | String | 최초등록자명 |
| frstRegDt | String | 최초등록일시 |
| lastMdfrNm | String | 최종수정자명 |
| lastMdfcnDt | String | 최종수정일시 |
| exsFileNm | String | 기존파일명 |
| atchFileSz | String | 첨부파일 크기 |
| atchFile | String | 첨부파일 |
| atchFileExtnNm | String | 첨부파일 확장자명 |
| cntrPlcSn | String | 센터장소일련번호 |
| tnumnailYn | String | 썸네일 여부 |
| cntrPlcExpln | String | 센터상세설명 |

---

## 6. 기본계획과제 API
- **요청 URL:** https://www.youthcenter.go.kr/go/ythip/getBscPlanAsmt

### 요청 Parameter
| 항목 | 타입 | 필수여부 | 설명 |
|------|------|---------|------|
| apiKeyNm | String | Y | 인증키 |
| rtnType | String | Y | 반환형식(xml/json) |

### 출력 결과
| 항목 | 타입 | 설명 |
|------|------|------|
| bscPlanCycl | String | 기본계획차수 |
| bscPlanFcsAsmtNo | String | 기본계획중점과제번호 |
| asmtNm | String | 과제명 |
| bscPlanAsmtNo | String | 기본계획과제번호 |

---

## 7. 청년정책코드 API
- **요청 URL:** https://www.youthcenter.go.kr/go/ythip/getPolicyCode

### 요청 Parameter
| 항목 | 타입 | 필수여부 | 설명 |
|------|------|---------|------|
| apiKeyNm | String | Y | 인증키 |
| rtnType | String | Y | 반환형식(xml/json) |

### 출력 결과
| 항목 | 타입 | 설명 |
|------|------|------|
| cdGroupCd | String | 코드그룹코드 |
| comCd | String | 공통코드 |
| cdNm | String | 코드명 |
| cdExpln | String | 코드설명 |
| frstRegDt | String | 최초등록일시 |
| lastMdfcnDt | String | 최종수정일시 |
| cdGroupNm | String | 코드그룹명 |


