package com.karrot.domain.transaction;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.karrot.domain.user.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Entity
@Table(name = "transaction")
@Data
@Schema(name = "Transaction", description = "거래 정보")
public class Transaction {
    @Id
    @Schema(name = "id", description = "거래 ID")
    private Long id;

    @Schema(name = "productId", description = "상품 ID")
    private Long productId;

    @Schema(name = "buyerId", description = "구매자 ID")
    private Long buyerId;

    @Schema(name = "sellerId", description = "판매자 ID")
    private Long sellerId;

    @Schema(name = "transactionDateTime", description = "거래 일시")
    private String transactionDateTime;

    @Schema(name = "transactionAmount", description = "거래 금액")
    private int transactionAmount;

    @Schema(name = "transactionStatus", description = "거래 상태")
    private String transactionStatus;

    @Schema(name = "isDeleted", description = "거래 삭제여부")
    private boolean isDeleted;

    @ManyToOne
    @Schema(name = "createUser", description = "상품을 등록한 사용자 정보")
    private User createUser;

    @Schema(name = "createdDateTime", description = "상품 등록일시")
    private LocalDateTime createdDateTime;

    @ManyToOne
    @Schema(name = "modifyUser", description = "상품을 수정한 사용자 정보")
    private User modifyUser;

    @Schema(name = "modifiedDateTime", description = "상품 수정일시")
    private LocalDateTime modifiedDateTime;

    @ManyToOne
    @Schema(name = "deleteUser", description = "상품을 삭제한 사용자 정보")
    private User deleteUser;

    @Schema(name = "deletedDateTime", description = "상품 삭제일시")
    private LocalDateTime deletedDateTime;
}