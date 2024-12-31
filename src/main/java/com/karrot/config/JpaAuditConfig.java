package com.karrot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JpaAuditConfig
 * - JPA Audit 설정
 * - SpringApplication에서 설정하면 WebMvcTest에서 JPA 오류가 발생함
 *   - WebMvcTest는 Web 관련된 Bean만 로드하기 때문
 *   - JPA 관련 설정이 안된 상태에서 SpringApplication의 Audit이 설정되면 오류가 발생
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditConfig {
}
