package com.musinsa.mycoordinator.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.jackson.ProblemModule;

/**
 * Problem 라이브러리 설정
 */
@Configuration
public class ProblemConfig {
    @Autowired
    public void addProblemModule(ObjectMapper objectMapper) {
        objectMapper.registerModule(new ProblemModule());
    }
}
