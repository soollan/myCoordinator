package com.musinsa.mycoordinator.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musinsa.mycoordinator.domain.code.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateProductRequest {

    @NotNull(message = "상품 ID는 필수값 입니다.")
    @Schema(description = "상품 ID", example = "1")
    private long productId;

    @Schema(description = "카테고리", example = "TOP")
    private ProductCategory category;

    @Schema(description = "금액", example = "5000")
    private int price;

    @Schema(description = "브랜드 ID", example = "1")
    private long brandId;
}
