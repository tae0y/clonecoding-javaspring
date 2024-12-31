package com.karrot.domain.user;

import org.hibernate.exception.ConstraintViolationException;

import org.hibernate.cfg.NotYetImplementedException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.karrot.global.common.ResponseDTOWrapper;
import com.karrot.global.common.ResponseStatusEnum;

/**
 * 전역 예외 처리 클래스
 */
@RestControllerAdvice
public class UsersControllerExceptionHandler {

    /**
     * NotYetImplementedException 예외 처리
     * @param e
     * @return
     */
    @ExceptionHandler(NotYetImplementedException.class)
    public ResponseEntity<String> handleNotYetImplementedException(NotYetImplementedException e) {
        e.printStackTrace();

        return new ResponseEntity<>("Not yet implemented", HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * TransactionSystemException 예외 처리
     * 
     * - TransactionSystemException는 트랜잭션 매니저에서 롤백, 커밋 등 실패시 발생하는 예외
     * - 이 메서드 내부에서는 해당 예외의 직접적인 원인을 찾아서 알맞은 응답코드를 반환함
     * @param e
     * @return
     */
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ResponseDTOWrapper<?>> handleTransactionSystemException(TransactionSystemException e) {
        e.printStackTrace();

        Throwable cause = e.getCause();
        while (cause != null && !(cause instanceof ConstraintViolationException)) {
            cause = cause.getCause();
        }

        ResponseDTOWrapper<?> response = new ResponseDTOWrapper<>();
        if (cause instanceof ConstraintViolationException) {
            response.setResponseMessage("Invalid input");
            response.setResponseStatus(ResponseStatusEnum.FAIL_INTERNAL);
            response.setOriginalStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.setResponseMessage("Internal server error");
        response.setResponseStatus(ResponseStatusEnum.FAIL_INTERNAL);
        response.setOriginalStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * DataIntegrityViolationException 예외 처리
     * 
     * - DataIntegrityViolationException는 데이터베이스단에서 데이터 무결성 제약조건 위반 시 발생하는 예외
     * - 이 메서드 내부에서는 해당 예외의 직접적인 원인을 찾아서 알맞은 응답코드를 반환함
     * @param e
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDTOWrapper<?>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        e.printStackTrace();

        Throwable cause = e.getCause();
        while (cause != null && !(cause instanceof ConstraintViolationException)) {
            cause = cause.getCause();
        }

        ResponseDTOWrapper<?> response = new ResponseDTOWrapper<>();
        if (cause instanceof ConstraintViolationException) {
            response.setResponseMessage("Invalid input");
            response.setResponseStatus(ResponseStatusEnum.FAIL_INTERNAL);
            response.setOriginalStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.setResponseMessage("Internal server error");
        response.setResponseStatus(ResponseStatusEnum.FAIL_INTERNAL);
        response.setOriginalStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * NullPointerException 예외 처리
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseDTOWrapper<?>> handleNullPointerException(NullPointerException e) {
        e.printStackTrace();

        ResponseDTOWrapper<?> response = new ResponseDTOWrapper<>();
        response.setResponseMessage("Internal server error");
        response.setResponseStatus(ResponseStatusEnum.FAIL_INTERNAL);
        response.setOriginalStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception 예외 처리
     * 
     * - 그외 알려지지 않은 예외에 대해서는 503을 반환하기로함
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTOWrapper<?>> handleException(Exception e) {
        e.printStackTrace();

        ResponseDTOWrapper<?> response = new ResponseDTOWrapper<>();
        response.setResponseMessage("Internal server error");
        response.setResponseStatus(ResponseStatusEnum.FAIL_INTERNAL);
        response.setOriginalStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}