package com.karrot.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Entity
@Table(name="user")
@Data
@Schema(name = "User", description = "사용자 정보")
public class User {
    @Id
    @Schema(name = "id", description = "사용자 ID")
    private Long id;
    @Schema(name = "name", description = "사용자 이름")
    private String name;
    @Schema(name = "introduction", description = "사용자 소개글")
    private String introduction;

    @Schema(name = "isActive", description = "사용자 활성화여부")
    private boolean isActive;

    @Schema(name = "createProgramId", description = "사용자 등록 프로그램 Id(자체 회원가입, OAuth 등)")
    private String createProgramId;
    @Schema(name = "createdDateTime", description = "상품 등록일시")
    private LocalDateTime createdDateTime;
    @Schema(name = "modifyProgramId", description = "사용자 수정 프로그램 Id(자체 프로필수정, 데이터연동 등)")
    private String modifyProgramId;
    @Schema(name = "modifiedDateTime", description = "상품 수정일시")
    private LocalDateTime modifiedDateTime;
    @Schema(name = "deleteProgramId", description = "사용자 삭제 프로그램 Id(자체 회원탈퇴, 관리자 삭제, 개인정보 보존만료 등)")
    private String deleteProgramId;
    @Schema(name = "deletedDateTime", description = "상품 삭제일시")
    private LocalDateTime deletedDateTime;
}