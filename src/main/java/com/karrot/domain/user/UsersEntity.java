package com.karrot.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.karrot.global.jpaaudit.AuditableEntity;
import com.karrot.global.jpaaudit.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name="users")
@EntityListeners(AuditingEntityListener.class)
@Data
public class UsersEntity implements AuditableEntity {
    /**
     * 사용자 ID
     */
    @Id
    //TODO : [개선] H2, PostgreSQL 등 각 DB에 맞는 ID 생성 전략을 수립하여야함
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @NotNull
    private Long id;

    /**
     * 사용자 이름
     */
    @Column(nullable = false, length = 10)
    @NotNull
    @Size(max = 10)
    private String name;

    /**
     * 사용자 소개글
     */
    @Column(nullable = true, length = 255)
    @Nullable
    @Size(max = 255)
    private String introduction;

    /**
     * 사용자 활성화여부
     */
    @Column(nullable = false)
    @NotNull
    private boolean isActive;

    /**
     * 사용자 등록 프로그램 Id(자체 회원가입, OAuth 등)
     */
    @Column(nullable = false, length = 20)
    @NotNull
    @Size(max = 20)
    //@CreatedBy TODO: [개선] JPA Auditing을 사용하여 사용자 ID를 자동으로 생성하도록 수정, 그럼 시스템단의 변경은?
    private String createProgramId;

    /**
     * 사용자 등록일시
     */
    @Column(nullable = false)
    @NotNull
    private LocalDateTime createdDateTime;

    /**
     * 사용자 수정 프로그램 Id(자체 프로필수정, 데이터연동 등)
     */
    @Column(nullable = true, length = 20)
    @Nullable
    @Size(max = 20)
    //@LastModifiedBy TODO: [개선] JPA Auditing을 사용하여 사용자 ID를 자동으로 생성하도록 수정, 그럼 시스템단의 변경은?
    private String modifyProgramId;

    /**
     * 사용자 수정일시
     */
    @Column(nullable = true)
    @Nullable
    private LocalDateTime modifiedDateTime;

    /** 
     * 사용자 삭제 프로그램 Id(자체 회원탈퇴, 관리자 삭제, 개인정보 보존만료 등)
     */
    @Column(nullable = true, length = 20)
    @Nullable
    @Size(max = 20)
    private String deleteProgramId;

    /**
     * 사용자 삭제일시
     */
    @Column(nullable = true)
    @Nullable
    private LocalDateTime deletedDateTime;

    /**
     * UsersEntity 기본 생성자
     */
    public UsersEntity() {
    }

    public UsersEntity(UsersRequestDTO request){
        this.name = request.getName();
        this.introduction = request.getIntroduction();
        this.isActive = true; //TODO: 사용자 등록시 바로 활성화? 이후 인증후 활성화?
        this.createProgramId = request.getCreateProgramId();
        this.modifyProgramId = request.getModifyProgramId();
        this.deleteProgramId = request.getDeleteProgramId();
    }
}