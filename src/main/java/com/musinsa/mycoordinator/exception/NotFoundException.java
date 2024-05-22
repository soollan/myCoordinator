package com.musinsa.mycoordinator.exception;

public class NotFoundException extends BusinessException {
    public NotFoundException() {
        super("자원을 찾을 수 없음");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
