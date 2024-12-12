package com.karrot.domain.product;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Products", description = "상품 정보")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    /**
     * <ul>
     *   <li>name : dummy
     *   <li>desc : OpenAPI 문서에 객체 표시되게 하기 위한 dummy API
     * </ul>
     * @param request
     * @return
     */

     @Operation(summary ="dummy", description = "products dummpy api!")
     @PostMapping("/")
     public ResponseEntity<ProductsResponseDTO> dummy(ProductsRequestDTO request) {
         throw new NotYetImplementedException();
     }
}