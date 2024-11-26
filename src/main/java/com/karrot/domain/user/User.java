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
    @Schema(name = "ID", description = "사용자 ID")
    private Long id;
    @Schema(name = "이름", description = "사용자 이름")
    private String name;
    @Schema(name = "소개글", description = "사용자 소개글")
    private String introduction;

    @Schema(name = "활성화여부", description = "사용자 활성화여부")
    private boolean isActive;

    @Schema(name = "등록프로그램Id", description = "사용자 등록 프로그램 Id(자체 회원가입, OAuth 등)")
    private String createProgramId;
    @Schema(name = "등록일시", description = "상품 등록일시")
    private LocalDateTime createdDateTime;
    @Schema(name = "수정프로그램Id", description = "사용자 수정 프로그램 Id(자체 프로필수정, 데이터연동 등)")
    private String modifyProgramId;
    @Schema(name = "수정일시", description = "상품 수정일시")
    private LocalDateTime modifiedDateTime;
    @Schema(name = "삭제프로그램Id", description = "사용자 삭제 프로그램 Id(자체 회원탈퇴, 관리자 삭제, 개인정보 보존만료 등)")
    private String deleteProgramId;
    @Schema(name = "삭제일시", description = "상품 삭제일시")
    private LocalDateTime deletedDateTime;
}