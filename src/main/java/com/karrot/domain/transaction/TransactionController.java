package com.karrot.domain.transaction;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Transactions", description = "거래 정보")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    /**
     * <ul>
     *   <li>name : dummy
     *   <li>desc : OpenAPI 문서에 객체 표시되게 하기 위한 dummy API
     * </ul>
     * @param request
     * @return
     */

    @Operation(summary ="dummy", description = "transactions dummpy api!")
    @PostMapping("/")
    public ResponseEntity<TransactionsResponseDTO> dummy(TransactionsRequestDTO request) {
        throw new NotYetImplementedException();
    }

}
