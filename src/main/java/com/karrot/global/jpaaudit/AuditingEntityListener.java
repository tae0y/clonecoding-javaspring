package com.karrot.global.jpaaudit;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * 엔티티 생성, 수정, 삭제시 자동으로 날짜를 기록하는 리스너
 */
public class AuditingEntityListener {

    /**
     * 생성일시 자동 생성
     * @param entity
     */
    @PrePersist
    public void setCreatedDate(Object entity) {
        if (entity instanceof AuditableEntity) {
            ((AuditableEntity) entity).setCreatedDateTime(LocalDateTime.now());
        }
    }

    /**
     * 수정일시 자동 생성
     * @param entity
     */
    @PreUpdate
    public void setUpdatedDate(Object entity) {
        if (entity instanceof AuditableEntity) {
            ((AuditableEntity) entity).setModifiedDateTime(LocalDateTime.now());
        }
    }

    /**
     * 삭제일시 자동 생성
     * @param entity
     */
    @PreRemove
    public void preRemove(Object entity) {
        if (entity instanceof AuditableEntity) {
            ((AuditableEntity) entity).setDeletedDateTime(LocalDateTime.now());
        }
    }
}