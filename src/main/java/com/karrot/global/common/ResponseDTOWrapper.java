package com.karrot.global.common;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * ResponseDTOWrapper
 * - ResponseDTO를 감싸는 Wrapper 클래스
 * - 응답코드, 메시지, 데이터를 감싸서 응답 언마샬/로딩시 같은 로직으로 처리할 수 있도록 함
 */
@Data
public class ResponseDTOWrapper<T> {
    /**
     * data
     * 실제 응답 데이터
     */
    private List<T> data;

    /**
     * responseMessage
     * 사용자에게 표출되는 응답 메시지
     */
    private String responseMessage;

    /**
     * responseStatus
     * 사용자에게 표출되는 응답코드
     */
    private ResponseStatusEnum responseStatus;

    /**
     * originalStatus
     * 사용자에게 노출되지 않는 실제 HTTP 응답코드
     */
    private HttpStatus originalStatus;

    /**
     * ResponseDTOWrapper
     * @param data 객체가 여러개일 때도 대응할 수 있도록 List로 받음
     * @param responseStatus 사용자에게 표출되는 응답 메시지
     * @param responseStatus 사용자에게 표출되는 응답코드
     * @param originalStatus 사용자에게 노출되지 않는 실제 HTTP 응답코드
     */
    public ResponseDTOWrapper(List<T> data, String responseMessage, ResponseStatusEnum responseStatus, HttpStatus originalStatus) {
        this.data = data;
        this.responseMessage = responseMessage;
        this.responseStatus = responseStatus;
        this.originalStatus = originalStatus;
    }

    /**
     * ResponseDTOWrapper
     * - 기본 생성자
     */
    public ResponseDTOWrapper() {
    }
}