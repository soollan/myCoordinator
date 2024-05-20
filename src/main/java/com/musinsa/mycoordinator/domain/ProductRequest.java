package com.musinsa.mycoordinator.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductRequest {

    @NotBlank(message = "카테고리는 필수값 입니다.")
    @Schema(description = "카테고리", example = "TOP")
    private ProductCategory category;

    @NotNull(message = "금액은 필수값 입니다.")
    @Schema(description = "금액", example = "5000")
    private Integer price;

    @NotNull(message = "브랜드ID는 필수값 입니다.")
    @Schema(description = "브랜드 ID", example = "1")
    private Long brandId;
}
