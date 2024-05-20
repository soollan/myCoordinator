package com.musinsa.mycoordinator.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.musinsa.mycoordinator.entity.ProductEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LowestResponse {

    @Schema(description = "최저")
    BrandResponse lowest;

    public LowestResponse(String brand, int totalPrice, List<ProductEntity> category) {
        this.lowest = new BrandResponse(brand, totalPrice, category);
    }

    @Getter
    private static class BrandResponse {

        @Schema(description = "브랜드")
        private String brand;

        @Schema(description = "가격 정보")
        private List<CategoryAndPriceResponse> category;

        @Schema(description = "총액")
        private Integer totalPrice;

        private BrandResponse(String brand, int totalPrice, List<ProductEntity> category) {
            this.brand = brand;
            this.totalPrice = totalPrice;
            this.category = CategoryAndPriceResponse.fromList(category);
        }
    }

    @Getter
    @AllArgsConstructor
    private static class CategoryAndPriceResponse {

        @Schema(description = "카테고리")
        @JsonFormat(shape = JsonFormat.Shape.OBJECT)
        private ProductCategory category;

        @Schema(description = "가격")
        private Integer price;

        private static CategoryAndPriceResponse from(ProductEntity productEntity) {
            return new CategoryAndPriceResponse(
                    productEntity.getCategory()
                    , productEntity.getPrice());
        }

        private static List<CategoryAndPriceResponse> fromList(List<ProductEntity> productEntities) {
            return productEntities.stream()
                    .map(CategoryAndPriceResponse::from)
                    .collect(Collectors.toList());

        }
    }
}
