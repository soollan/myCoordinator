package com.musinsa.mycoordinator.deploy;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Hidden
@RequestMapping("/deployTest")
public class HealthCheckApi {
    @GetMapping
    @Operation(summary = "배포 헬스 체크", description = "차량 상태에 대해 체크합니다.")
    public String hornRequest() {
        return "OK";
    }
}
