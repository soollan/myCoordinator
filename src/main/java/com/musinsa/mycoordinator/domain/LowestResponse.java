package com.musinsa.mycoordinator.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LowestResponse {

    @Schema(description = "최저")
    BrandResponse lowest;

    public LowestResponse(String brand, int totalPrice, List<ItemEntity> category) {
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

        private BrandResponse(String brand, int totalPrice, List<ItemEntity> category) {
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
        private ItemCategory category;

        @Schema(description = "가격")
        private Integer price;

        private static CategoryAndPriceResponse from(ItemEntity itemEntity) {
            return new CategoryAndPriceResponse(
                    itemEntity.getCategory()
                    , itemEntity.getPrice());
        }

        private static List<CategoryAndPriceResponse> fromList(List<ItemEntity> itemEntities) {
            return itemEntities.stream()
                    .map(CategoryAndPriceResponse::from)
                    .collect(Collectors.toList());

        }
    }
}
