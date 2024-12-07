package com.karrot.domain.product;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.karrot.domain.user.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Entity
@Table(name="products")
@Data
@Schema(name = "Product", description = "상품 정보")
public class Products {
    @Id
    @Schema(name = "id", description = "상품 ID")
    private Long id;
    
    @Schema(name = "name", description = "상품 이름")
    private String name;
    
    @Schema(name = "price", description = "상품 가격")
    private int price;
    
    @Schema(name = "category", description = "상품 카테고리")
    private String category;

    @Schema(name = "description", description = "상품 설명")
    private int description;

    @Schema(name = "isPrivate", description = "상품 비공개여부")
    private boolean isPrivate;
    
    @Schema(name = "isSoldOut", description = "상품 판매여부")
    private boolean isSoldOut;

    @Schema(name = "isDeleted", description = "상품 삭제여부")
    private boolean isDeleted;

    @ManyToOne
    @Schema(name = "createUser", description = "상품을 등록한 사용자 정보")
    private Users createUser;

    @Schema(name = "createdDateTime", description = "상품 등록일시")
    private LocalDateTime createdDateTime;

    @ManyToOne
    @Schema(name = "modifyUser", description = "상품을 수정한 사용자 정보")
    private Users modifyUser;

    @Schema(name = "modifiedDateTime", description = "상품 수정일시")
    private LocalDateTime modifiedDateTime;

    @ManyToOne
    @Schema(name = "deleteUser", description = "상품을 삭제한 사용자 정보")
    private Users deleteUser;

    @Schema(name = "deletedDateTime", description = "상품 삭제일시")
    private LocalDateTime deletedDateTime;
}