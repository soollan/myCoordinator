package com.musinsa.mycoordinator.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.mycoordinator.domain.code.ProductCategory;
import com.musinsa.mycoordinator.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BrandResponse {

    @JsonProperty(value = "브랜드")
    private String brand;

    @JsonProperty(value = "카테고리")
    private List<CategoryAndPriceResponse> category;

    @JsonProperty(value = "총액")
    private Integer totalPrice;

    public BrandResponse(String brand, int totalPrice, List<ProductEntity> products) {
        this.brand = brand;
        this.totalPrice = totalPrice;
        this.category = CategoryAndPriceResponse.fromList(products);
    }

    @Getter
    @AllArgsConstructor
    private static class CategoryAndPriceResponse {

        @JsonProperty(value = "카테고리")
        @JsonFormat(shape = JsonFormat.Shape.OBJECT)
        private ProductCategory category;

        @JsonProperty(value = "가격")
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
