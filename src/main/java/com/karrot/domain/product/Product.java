package com.karrot.domain.product;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.karrot.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Entity
@Table(name="product")
@Data
@Schema(name = "Product", description = "상품 정보")
public class Product {
    @Id
    @Schema(name = "ID", description = "상품 ID")
    private Long id;
    
    @Schema(name = "이름", description = "상품 이름")
    private String name;
    
    @Schema(name = "가격", description = "상품 가격")
    private int price;
    
    @Schema(name = "카테고리", description = "상품 카테고리")
    private String category;

    @Schema(name = "설명", description = "상품 설명")
    private int description;

    @Schema(name = "비공개여부", description = "상품 비공개여부")
    private boolean isPrivate;
    
    @Schema(name = "판매여부", description = "상품 판매여부")
    private boolean isSoldOut;

    @Schema(name = "삭제여부", description = "상품 삭제여부")
    private boolean isDeleted;

    @ManyToOne
    @Schema(name = "등록자", description = "상품을 등록한 사용자 정보")
    private User createUser;

    @Schema(name = "등록일시", description = "상품 등록일시")
    private LocalDateTime createdDateTime;

    @ManyToOne
    @Schema(name = "수정자", description = "상품을 수정한 사용자 정보")
    private User modifyUser;

    @Schema(name = "수정일시", description = "상품 수정일시")
    private LocalDateTime modifiedDateTime;

    @ManyToOne
    @Schema(name = "삭제자", description = "상품을 삭제한 사용자 정보")
    private User deleteUser;

    @Schema(name = "삭제일시", description = "상품 삭제일시")
    private LocalDateTime deletedDateTime;
}