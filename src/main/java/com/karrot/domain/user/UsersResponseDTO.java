package com.karrot.domain.user;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "User", description = "사용자 정보")
public class UsersResponseDTO {
    /**
     * 사용자 ID
     */
    @Schema(name = "id", description = "사용자 ID", required = true, nullable = false, example = "1")
    private Long id;

    /**
     * 사용자 이름
     */
    @Schema(name = "name", description = "사용자 이름", required = false, nullable = false, example = "John Doe", maxLength = 10)
    private String name;

    /**
     * 사용자 소개글
     */
    @Schema(name = "introduction", description = "사용자 소개글", required = false, nullable = true, example = "Hello, I am John.", maxLength = 255)
    private String introduction;

    /**
     * 사용자 활성화여부
     */
    @Schema(name = "isActive", description = "사용자 활성화여부", required = false, nullable = false, example = "true")
    private boolean isActive;

    /**
     * 사용자 등록 프로그램 Id(자체 회원가입, OAuth 등)
     */
    @Schema(name = "createProgramId", description = "사용자 등록 프로그램 Id(자체 회원가입, OAuth 등)", required = false, nullable = false, example = "oauth", maxLength = 20)
    private String createProgramId;

    /**
     * 사용자 등록일시
     */
    @Schema(name = "createdDateTime", description = "사용자 등록일시", required = false, nullable = false, example = "2023-01-01T12:00:00")
    private LocalDateTime createdDateTime;

    /**
     * 사용자 수정 프로그램 Id(자체 프로필수정, 데이터연동 등)
     */
    @Schema(name = "modifyProgramId", description = "사용자 수정 프로그램 Id(자체 프로필수정, 데이터연동 등)", required = true, nullable = false, example = "profile_edit", maxLength = 20)
    private String modifyProgramId;

    /**
     * 사용자 수정일시
     */
    @Schema(name = "modifiedDateTime", description = "사용자 수정일시", required = true, nullable = false, example = "2023-01-02T12:00:00")
    private LocalDateTime modifiedDateTime;

    /**
     * 사용자 삭제 프로그램 Id(자체 회원탈퇴, 관리자 삭제, 개인정보 보존만료 등)
     */
    @Schema(name = "deleteProgramId", description = "사용자 삭제 프로그램 Id(자체 회원탈퇴, 관리자 삭제, 개인정보 보존만료 등)", required = false, nullable = true, example = "admin_delete", maxLength = 20)
    private String deleteProgramId;

    /**
     * 사용자 삭제일시
     */
    @Schema(name = "deletedDateTime", description = "사용자 삭제일시", required = false, nullable = true, example = "2023-01-03T12:00:00")
    private LocalDateTime deletedDateTime;

    /**
     * UsersResponseDTO 기본 생성자
     */
    public UsersResponseDTO() {
    }

    /**
     * UsersResponseDTO 생성자
     * @param entity UsersEntity
     */
    public UsersResponseDTO(UsersEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.introduction = entity.getIntroduction();
        this.isActive = entity.isActive();
        this.createProgramId = entity.getCreateProgramId();
        this.createdDateTime = entity.getCreatedDateTime();
        this.modifyProgramId = entity.getModifyProgramId();
        this.modifiedDateTime = entity.getModifiedDateTime();
        this.deleteProgramId = entity.getDeleteProgramId();
        this.deletedDateTime = entity.getDeletedDateTime();
    }
}