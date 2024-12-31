package com.karrot.global.jpaaudit;

import java.time.LocalDateTime;

/**
 * 엔티티 생성, 수정, 삭제시 자동으로 날짜를 기록하는 인터페이스
 * - createdDateTime, modifiedDateTime, deletedDateTime 필드가 필수
 */
public interface AuditableEntity {
    void setCreatedDateTime(LocalDateTime createdDateTime);
    void setModifiedDateTime(LocalDateTime modifiedDateTime);
    void setDeletedDateTime(LocalDateTime deletedDateTime);
}