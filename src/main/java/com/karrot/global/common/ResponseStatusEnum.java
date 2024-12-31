package com.karrot.global.common;

/**
 * ResponseStatusEnum
 * - 응답코드를 정의한 Enum 클래스
 * - 응답코드를 정의하여 사용할 때, ResponseDTOWrapper 클래스에서 사용
 */
public enum ResponseStatusEnum {
    /**
     * SUCCESS
     * 성공
     */
    SUCCESS(100),

    /**
     * FAIL_INTENDED
     * 의도된 실패
     */
    FAIL_INTENDED(900),

    /**
     * FAIL_INTERNAL
     * 내부 서버 오류
     */
    FAIL_INTERNAL(910),

    /**
     * FAIL_OUTTERNAL
     * 외부 서버 오류
     */
    FAIL_OUTTERNAL(920);

    private final int code;

    ResponseStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}