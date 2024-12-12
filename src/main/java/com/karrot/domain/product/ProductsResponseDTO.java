package com.karrot.domain.product;

import java.time.LocalDateTime;

import com.karrot.domain.user.UsersEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Product", description = "상품 정보")
public class ProductsResponseDTO {
    @Schema(name = "name", description = "상품 이름", required = true, nullable = false, example = "Apple", maxLength = 100)
    private String name;

    @Schema(name = "price", description = "상품 가격", required = true, nullable = false, example = "1000", minimum = "0")
    private int price;

    @Schema(name = "category", description = "상품 카테고리", required = false, nullable = true, example = "Fruit", maxLength = 100)
    private String category;

    @Schema(name = "description", description = "상품 설명", required = false, nullable = true, example = "This is an apple.", maxLength = 1000)
    private String description;

    @Schema(name = "isPrivate", description = "상품 비공개여부", required = true, nullable = false, example = "false")
    private boolean isPrivate;

    @Schema(name = "isSoldOut", description = "상품 판매여부", required = true, nullable = false, example = "false")
    private boolean isSoldOut;

    @Schema(name = "isDeleted", description = "상품 삭제여부", required = true, nullable = false, example = "false")
    private boolean isDeleted;

    @Schema(name = "createUser", description = "상품을 등록한 사용자 정보", required = true, nullable = false)
    private UsersEntity createUser;

    @Schema(name = "createdDateTime", description = "상품 등록일시")
    private LocalDateTime createdDateTime;

    @Schema(name = "modifyUser", description = "상품을 수정한 사용자 정보", required = false, nullable = true)
    private UsersEntity modifyUser;

    @Schema(name = "modifiedDateTime", description = "상품 수정일시")
    private LocalDateTime modifiedDateTime;

    @Schema(name = "deleteUser", description = "상품을 삭제한 사용자 정보", required = false, nullable = true)
    private UsersEntity deleteUser;

    @Schema(name = "deletedDateTime", description = "상품 삭제일시")
    private LocalDateTime deletedDateTime;
}