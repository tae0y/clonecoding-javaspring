package com.karrot.domain.transaction;

import java.time.LocalDateTime;

import com.karrot.domain.product.ProductsEntity;
import com.karrot.domain.user.UsersEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransactionsResponseDTO {
    /**
     * 거래 ID
     */
    @Schema(name = "id", description = "거래 ID", required = true, nullable = false, example = "1")
    private Long id;

    /**
     * 거래할 상품
     */
    @Schema(name = "product", description = "거래할 상품", required = true, nullable = false)
    private ProductsEntity product;

    /**
     * 구매자
     */
    @Schema(name = "buyer", description = "구매자", required = true, nullable = false)
    private UsersEntity buyer;

    /**
     * 판매자
     */
    @Schema(name = "seller", description = "판매자", required = true, nullable = false)
    private UsersEntity seller;

    /**
     * 거래 완료 일시
     */
    @Schema(name = "transactionDateTime", description = "거래 일시", required = false, nullable = true, example = "2023-01-01T12:00:00")
    private LocalDateTime transactionDateTime;

    /**
     * 거래 금액
     */
    @Schema(name = "transactionAmount", description = "거래 금액", required = false, nullable = true, example = "10000")
    private Integer transactionAmount;

    /**
     * 거래 상태
     */
    @Schema(name = "isOpen", description = "거래 상태", required = true, nullable = false, example = "true")
    private String isOpen;

    /**
     * 거래 삭제여부
     */
    @Schema(name = "isDeleted", description = "거래 삭제여부", required = true, nullable = false, example = "false")
    private Boolean isDeleted;

    /**
     * 거래 등록자
     */
    //TODO: createdUserId인지, createUsers로 할지 고민 필요
    @Schema(name = "createUser", description = "거래 등록자", required = true, nullable = false)
    private UsersEntity createUser;

    /**
     * 거래 등록일시
     */
    @Schema(name = "createdDateTime", description = "거래 등록일시", required = true, nullable = false, example = "2023-01-01T12:00:00")
    private LocalDateTime createdDateTime;

    /**
     * 거래 수정자
     */
    @Schema(name = "modifyUser", description = "거래 수정자", required = false, nullable = true)
    private UsersEntity modifyUser;

    /**
     * 거래 수정일시
     */
    @Schema(name = "modifiedDateTime", description = "거래 수정일시", required = false, nullable = true, example = "2023-01-02T12:00:00")
    private LocalDateTime modifiedDateTime;

    /**
     * 거래 삭제자
     */
    @Schema(name = "deleteUser", description = "거래 삭제자", required = false, nullable = true)
    private UsersEntity deleteUser;

    /**
     * 거래 삭제일시
     */
    @Schema(name = "deletedDateTime", description = "거래 삭제일시", required = false, nullable = true, example = "2023-01-03T12:00:00")
    private LocalDateTime deletedDateTime;
}