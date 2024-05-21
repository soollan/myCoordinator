package com.musinsa.mycoordinator.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musinsa.mycoordinator.domain.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateProductRequest {

    @NotNull(message = "상품 ID는 필수값 입니다.")
    @Schema(description = "상품 ID", example = "1")
    private Long productId;

    @Schema(description = "카테고리", example = "TOP")
    private ProductCategory category;

    @Schema(description = "금액", example = "5000")
    private int price;

    @Schema(description = "브랜드 ID", example = "1")
    private long brandId;
}
