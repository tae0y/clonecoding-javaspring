package com.karrot.domain.product;

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
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.Nullable;

import com.karrot.domain.user.UsersEntity;
import lombok.Data;

@Entity
@Table(name="products")
@Data
public class ProductsEntity {
    /**
     * 상품 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @NotNull
    private Long id;

    /**
     * 상품 이름
     */
    @Column(nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    private String name;

    /**
     * 상품 가격
     */
    @Column(nullable = false)
    @NotNull
    @Min(0)
    private Integer price;

    /**
     * 상품 카테고리
     */
    @Column(nullable = true, length = 100)
    @Nullable
    @Size(max = 100)
    private String category;

    /**
     * 상품 설명
     */
    @Column(nullable = true, length = 1000)
    @Nullable
    @Size(max = 1000)
    private String description;

    /**
     * 상품 비공개여부
     */
    @Column(nullable = false)
    @NotNull
    private boolean isPrivate;

    /**
     * 상품 판매여부
     */
    @Column(nullable = false)
    @NotNull
    private boolean isSoldOut;

    /**
     * 상품 삭제여부
     */
    @Column(nullable = false)
    @NotNull
    private boolean isDeleted;

    /**
     * 상품을 등록한 사용자 정보
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "createUserId", nullable = false)
    private UsersEntity createUser;

    /**
     * 상품 등록일시
     */
    @Column(nullable = false)
    @NotNull
    @CreatedDate
    private LocalDateTime createdDateTime;

    /**
     * 상품을 수정한 사용자 정보
     */
    @Nullable
    @ManyToOne
    @JoinColumn(name = "modifyUserId", nullable = true)
    private UsersEntity modifyUser;

    /**
     * 상품 수정일시
     */
    @Column(nullable = true)
    @Nullable
    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

    /**
     * 상품을 삭제한 사용자 정보
     */
    @Nullable
    @ManyToOne
    @JoinColumn(name = "deleteUserId", nullable = true)
    private UsersEntity deleteUser;

    /**
     * 상품 삭제일시
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