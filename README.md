본 서비스는 다음 네 가지 OpenAPI를 사용하여 데이터를 수집하고 저장합니다:

1. **청년정책 API**
    - 청년 정책 및 혜택 정보를 조회 및 저장합니다.

2. **청년콘텐츠 API**
    - 청년을 위한 콘텐츠 및 프로그램 정보를 조회 및 저장합니다.

3. **청년센터 API**
    - 전국의 청년센터 정보를 조회 및 저장합니다.

4. **청년정책코드 API**
    - 정책에 사용되는 다양한 코드 체계를 조회 및 저장합니다.

---

## 주요 기능 및 엔드포인트

### 데이터 저장 (웹 인터페이스를 통해 호출 가능)

- 정책 API 데이터 저장
  ```
  GET /fetch-policies-stream
  ```

- 청년콘텐츠 API 데이터 저장
  ```
  GET /fetch-contents-stream
  ```

- 청년센터 API 데이터 저장
  ```
  GET /fetch-centers-stream
  ```

- 정책코드 API 데이터 저장
  ```
  GET /fetch-policy-codes-stream
  ```

### 웹 인터페이스

사용자는 다음과 같은 웹 인터페이스를 통해 데이터를 저장할 수 있습니다.

- 브라우저에서 애플리케이션 URL (`http://localhost:8080`)로 접근합니다.
- 제공된 버튼을 클릭하면 각 API의 데이터가 저장됩니다.
- 화면에서 진행 상황과 완료 여부를 확인할 수 있습니다.

---

## 프로젝트 구조

```
src/main/java/org/example/youthcenterapi
├── common
│   └── dto
│       └── PagingDto.java
├── policy
│   ├── controller
│   ├── service
│   ├── dto
│   ├── entity
│   └── repository
├── content
│   ├── controller
│   ├── service
│   ├── dto
│   ├── entity
│   └── repository
├── center
│   ├── controller
│   ├── service
│   ├── dto
│   ├── entity
│   └── repository
└── code
    ├── controller
    ├── service
    ├── dto
    ├── entity
    └── repository
```

각 패키지는 독립적인 API 관련 기능을 포함하며 다음과 같이 구성됩니다:

- `controller`: REST API 요청을 처리하는 컨트롤러 클래스
- `service`: 비즈니스 로직을 처리하는 서비스 클래스
- `dto`: API 응답 데이터를 매핑하는 DTO 클래스
- `entity`: 데이터베이스 테이블과 매핑되는 JPA 엔티티 클래스
- `repository`: 데이터 접근을 위한 JPA Repository 인터페이스

---

## 환경 설정 및 실행 방법

- Java 17 설치
- MySQL 데이터베이스 설정 (예: Aiven)
- 각 API의 인가 코드(API Key)

### 설정 방법
1. 데이터베이스 연결 및 API 키 설정  
   `src/main/resources/application-secret.yml` 파일을 생성하고 아래 내용을 입력합니다.
```yaml
spring:
  datasource:
    url: jdbc:mysql://<host>:<port>/<dbname>?useSSL=true
    username: <username>
    password: <password>

api:
  youth-policy-key: YOUR_POLICY_API_KEY
  youth-content-key: YOUR_CONTENT_API_KEY
  youth-center-key: YOUR_CENTER_API_KEY
  youth-policy-code-key: YOUR_POLICY_CODE_API_KEY
```

## 데이터베이스

애플리케이션 실행 후 데이터는 다음과 같은 테이블에 저장됩니다:

- `youth_policy` (청년정책 API 데이터)
- `youth_content` (청년콘텐츠 API 데이터)
- `youth_center` (청년센터 API 데이터)
- `youth_policy_code` (청년정책코드 API 데이터)
