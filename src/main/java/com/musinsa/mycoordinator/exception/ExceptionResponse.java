package com.musinsa.mycoordinator.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    private String message;
    private String details;

    public ExceptionResponse(HttpStatus status, String details) {
        this.message = status.getReasonPhrase();
        this.details = details;
    }
}
