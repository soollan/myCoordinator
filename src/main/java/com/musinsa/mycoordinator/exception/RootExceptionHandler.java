package com.musinsa.mycoordinator.exception;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zalando.problem.Problem;

import static org.zalando.problem.Status.BAD_REQUEST;
import static org.zalando.problem.Status.INTERNAL_SERVER_ERROR;
import static org.zalando.problem.Status.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class RootExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
            @Content(schema = @Schema(implementation = ProblemDetail.class))})
    public ResponseEntity<Problem> globalExceptionHandler(Exception e) {
        log.error("치명적인 에러 발생", e);

        Problem problem = Problem.builder()
                .withStatus(INTERNAL_SERVER_ERROR)
                .withTitle(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(problem);
    }

    @ExceptionHandler(BusinessException.class)
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ProblemDetail.class))})
    public ResponseEntity<Problem> businessExceptionHandler(BusinessException e) {
        log.error("에러 발생", e);

        Problem problem = Problem.builder()
                .withStatus(BAD_REQUEST)
                .withTitle(e.getMessage())
                .withDetail(e.getDetailMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(problem);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Problem> handleNotFoundException(NotFoundException e) {
        log.warn("자원 없음", e);

        Problem problem = Problem.builder()
                .withTitle(e.getMessage())
                .withStatus(NOT_FOUND)
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(problem);
    }
}
