package com.karrot.domain.transaction;

import java.time.LocalDateTime;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.karrot.domain.product.ProductsEntity;
import com.karrot.domain.user.UsersEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransactionsRequestDTO {
    /**
     * 거래 ID
     */
    @NotNull
    @Schema(name = "id", description = "거래 ID", required = true, nullable = false, example = "1")
    private Long id;

    /**
     * 거래할 상품
     */
    @NotNull
    @Schema(name = "product", description = "거래할 상품", required = true, nullable = false)
    private ProductsEntity product;

    /**
     * 구매자
     */
    @NotNull
    @Schema(name = "buyer", description = "구매자", required = true, nullable = false)
    private UsersEntity buyer;

    /**
     * 판매자
     */
    @NotNull
    @Schema(name = "seller", description = "판매자", required = true, nullable = false)
    private UsersEntity seller;

    /**
     * 거래 완료 일시
     */
    @Nullable
    @Schema(name = "transactionDateTime", description = "거래 일시", required = false, nullable = true, example = "2023-01-01T12:00:00")
    private LocalDateTime transactionDateTime;

    /**
     * 거래 금액
     */
    @Nullable
    @Min(0)
    @Schema(name = "transactionAmount", description = "거래 금액", required = false, nullable = true, example = "10000")
    private Integer transactionAmount;

    /**
     * 거래 상태
     */
    @NotNull
    @Schema(name = "isOpen", description = "거래 상태", required = true, nullable = false, example = "true")
    private String isOpen;

    /**
     * 거래 삭제여부
     */
    @NotNull
    @Schema(name = "isDeleted", description = "거래 삭제여부", required = true, nullable = false, example = "false")
    private Boolean isDeleted;

    /**
     * 거래 등록자
     */
    //TODO: createdUserId인지, createUsers로 할지 고민 필요
    @NotNull
    @ManyToOne
    @Schema(name = "createUser", description = "거래 등록자", required = true, nullable = false)
    private UsersEntity createUser;

    /**
     * 거래 수정자
     */
    @Nullable
    @Schema(name = "modifyUser", description = "거래 수정자", required = false, nullable = true)
    private UsersEntity modifyUser;

    /**
     * 거래 삭제자
     */
    @Nullable
    @Schema(name = "deleteUser", description = "거래 삭제자", required = false, nullable = true)
    private UsersEntity deleteUser;
}