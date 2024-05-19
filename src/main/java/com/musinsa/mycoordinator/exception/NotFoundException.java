package com.musinsa.mycoordinator.exception;

/**
 * 404로 핸들링하기 위한 비즈니스 익셉션
 */
public class NotFoundException extends BusinessException {
    public NotFoundException() {
        super("자원을 찾을 수 없음");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
