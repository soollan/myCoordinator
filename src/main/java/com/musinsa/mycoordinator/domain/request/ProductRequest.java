package com.musinsa.mycoordinator.domain.request;

import com.musinsa.mycoordinator.domain.code.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {

    @NotNull(message = "카테고리는 필수값 입니다.")
    @Schema(description = "카테고리", example = "TOP")
    private ProductCategory category;

    @NotNull(message = "금액은 필수값 입니다.")
    @Schema(description = "금액", example = "5000")
    private int price;

    @NotNull(message = "브랜드 ID는 필수값 입니다.")
    @Schema(description = "브랜드 ID", example = "1")
    private long brandId;
}
