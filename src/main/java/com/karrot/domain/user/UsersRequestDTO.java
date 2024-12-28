package com.karrot.domain.user;

import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UsersRequestDTO", description = "사용자 정보")
public class UsersRequestDTO {
    /**
     * 사용자 이름
     */
    @Schema(name = "name", description = "사용자 이름", required = false, nullable = false, example = "John Doe", maxLength = 10)
    @Size(max = 10)
    @JsonProperty("name")
    private String name;

    /**
     * 사용자 소개글
     */
    @Schema(name = "introduction", description = "사용자 소개글", required = false, nullable = true, example = "Hello, I am John.", maxLength = 255)
    @Size(max = 255)
    @JsonProperty("introduction")
    private String introduction;

    /**
     * 사용자 활성화여부
     */
    @Schema(name = "isActive", description = "사용자 활성화여부", required = false, nullable = false, example = "true")
    @JsonProperty("isActive")
    private boolean isActive;

    /**
     * 사용자 등록 프로그램 Id(자체 회원가입, OAuth 등)
     */
    @Schema(name = "createProgramId", description = "사용자 등록 프로그램 Id(자체 회원가입, OAuth 등)", required = false, nullable = true, example = "oauth", maxLength = 20)
    @Size(max = 20)
    @JsonProperty("createProgramId")
    private String createProgramId;

    /**
     * 사용자 수정 프로그램 Id(자체 프로필수정, 데이터연동 등)
     */
    @Schema(name = "modifyProgramId", description = "사용자 수정 프로그램 Id(자체 프로필수정, 데이터연동 등)", required = true, nullable = true, example = "profile_edit", maxLength = 20)
    @Nullable
    @Size(max = 20)
    @JsonProperty("modifyProgramId")
    private String modifyProgramId;

    /**
     * 사용자 삭제 프로그램 Id(자체 회원탈퇴, 관리자 삭제, 개인정보 보존만료 등)
     */
    @Schema(name = "deleteProgramId", description = "사용자 삭제 프로그램 Id(자체 회원탈퇴, 관리자 삭제, 개인정보 보존만료 등)", required = false, nullable = true, example = "admin_delete", maxLength = 20)
    @Size(max = 20)
    @JsonProperty("deleteProgramId")
    private String deleteProgramId;

    /**
     * UsersRequestDTO 기본생성자
     */
    public UsersRequestDTO() {
    }
}