package com.karrot.domain.transaction;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.Nullable;

import com.karrot.domain.product.ProductsEntity;
import com.karrot.domain.user.UsersEntity;

import lombok.Data;

@Entity
@Table(name = "transactions")
@Data
public class TransactionsEntity {
    /**
     * 거래 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @NotNull
    private Long id;

    /**
     * 거래할 상품
     */
    @ManyToOne
    @NotNull
    @JoinColumn(name = "productId", nullable = false)
    private ProductsEntity product;

    /**
     * 구매자
     */
    @ManyToOne
    @NotNull
    @JoinColumn(name = "buyerId", nullable = false)
    private UsersEntity buyer;

    /**
     * 판매자
     */
    @ManyToOne
    @NotNull
    @JoinColumn(name = "sellerId", nullable = false)
    private UsersEntity seller;

    /**
     * 거래 완료 일시
     */
    @Column(nullable = true)
    @Nullable
    private LocalDateTime transactionDateTime;

    /**
     * 거래 금액
     */
    @Column(nullable = true)
    @Nullable
    @Min(0)
    private Integer transactionAmount;

    /**
     * 거래 상태
     */
    @Column(nullable = false)
    @NotNull
    private Boolean isOpen;

    /**
     * 거래 삭제여부
     */
    @Column(nullable = false)
    @NotNull
    private Boolean isDeleted;

    /**
     * 거래 등록자
     */
    //TODO: createdUserId인지, createUsers로 할지 고민 필요
    @NotNull
    @ManyToOne
    @JoinColumn(name = "createdUserId", nullable = false)
    private UsersEntity createUser;

    /**
     * 거래 등록일시
     */
    @Column(nullable = false)
    @NotNull
    @CreatedDate
    private LocalDateTime createdDateTime;

    /**
     * 거래 수정자
     */
    @ManyToOne
    @Nullable
    @JoinColumn(name = "modifiedUserId", nullable = true)
    private UsersEntity modifyUser;

    /**
     * 거래 수정일시
     */
    @Column(nullable = true)
    @Nullable
    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

    /**
     * 거래 삭제자
     */
    @ManyToOne
    @Nullable
    @JoinColumn(name = "deletedUserId", nullable = true)
    private UsersEntity deleteUser;

    /**
     * 거래 삭제일시
     */
    @Column(nullable = true)
    @Nullable
    private LocalDateTime deletedDateTime;

    /**
     * 삭제일시 자동 생성
     */
    @PreRemove
    public void preRemove() {
        this.deletedDateTime = LocalDateTime.now();
    }
}