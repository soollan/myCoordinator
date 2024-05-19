package com.musinsa.mycoordinator.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private String detailMessage;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message, String detailMessage) {
        super(message);
        this.detailMessage = detailMessage;
    }
}
