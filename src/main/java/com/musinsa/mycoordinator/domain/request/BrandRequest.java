package com.musinsa.mycoordinator.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class BrandRequest {

    @NotBlank(message = "브랜드명은 필수값 입니다.")
    @Schema(description = "브랜드명", example = "nike", minLength = 1, maxLength = 10)
    private String name;

}
