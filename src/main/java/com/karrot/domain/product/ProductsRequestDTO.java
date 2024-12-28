package com.karrot.domain.product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.karrot.domain.user.UsersEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Product", description = "상품 정보")
public class ProductsRequestDTO {
    /**
     * 상품 이름
     */
    @Schema(name = "name", description = "상품 이름", required = true, nullable = false, example = "Apple", maxLength = 100)
    @NotNull
    @Size(max = 100)
    private String name;

    /**
     * 상품 가격
     */
    @Schema(name = "price", description = "상품 가격", required = true, nullable = false, example = "1000", minimum = "0")
    @NotNull
    @Min(0)
    private int price;

    /**
     * 상품 카테고리
     */
    @Schema(name = "category", description = "상품 카테고리", required = false, nullable = true, example = "Fruit", maxLength = 100)
    @Size(max = 100)
    private String category;

    /**
     * 상품 설명
     */
    @Schema(name = "description", description = "상품 설명", required = false, nullable = true, example = "This is an apple.", maxLength = 1000)
    @Size(max = 1000)
    private String description;

    /**
     * 상품 비공개여부
     */
    @Schema(name = "isPrivate", description = "상품 비공개여부", required = true, nullable = false, example = "false")
    @NotNull
    private boolean isPrivate;

    /**
     *  상품 판매여부
     */
    @Schema(name = "isSoldOut", description = "상품 판매여부", required = true, nullable = false, example = "false")
    @NotNull
    private boolean isSoldOut;

    /**
     * 상품 삭제여부
     */
    @Schema(name = "isDeleted", description = "상품 삭제여부", required = true, nullable = false, example = "false")
    @NotNull
    private boolean isDeleted;

    /**
     * 상품을 등록한 사용자 정보
     */
    @Schema(name = "createUser", description = "상품을 등록한 사용자 정보", required = true, nullable = false)
    @NotNull
    private UsersEntity createUser;

    /**
     * 상품을 수정한 사용자 정보
     */
    @Schema(name = "modifyUser", description = "상품을 수정한 사용자 정보", required = false, nullable = true)
    @Nullable
    private UsersEntity modifyUser;

    /**
     * 상품을 삭제한 사용자 정보
     */
    @Schema(name = "deleteUser", description = "상품을 삭제한 사용자 정보", required = false, nullable = true)
    @Nullable
    private UsersEntity deleteUser;
}