package org.example.youthcenterapi;

import org.example.youthcenterapi.service.PolicyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class YouthcenterApiApplication {

    public static void main(String[] args) {
        // 스프링 부트 애플리케이션 실행 및 ApplicationContext 획득
        ConfigurableApplicationContext context = SpringApplication.run(YouthcenterApiApplication.class, args);

        // PolicyService 빈 가져오기
        PolicyService policyService = context.getBean(PolicyService.class);

        // OpenAPI 데이터를 DB에 저장하는 메서드 직접 호출
        policyService.fetchAndSavePolicies();
    }

    // RestTemplate 빈을 등록 (외부 API 호출을 위해 필요)
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
