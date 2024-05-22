package com.musinsa.mycoordinator.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.mycoordinator.domain.code.ProductCategory;
import com.musinsa.mycoordinator.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LowestHighestResponse {

    @JsonProperty(value = "카테고리")
    ProductCategory category;

    @JsonProperty(value = "최저가")
    List<BrandPrice> lowest;

    @JsonProperty(value = "최고가")
    List<BrandPrice> highest;

    public LowestHighestResponse(ProductCategory category, List<ProductEntity> lowest, List<ProductEntity> highest) {
        this.category = category;
        this.lowest = BrandPrice.fromList(lowest);
        this.highest = BrandPrice.fromList(highest);
    }

    @Getter
    @AllArgsConstructor
    private static class BrandPrice {
        @JsonProperty(value = "브랜드")
        String brand;

        @JsonProperty(value = "가격")
        Integer price;

        private static BrandPrice from(ProductEntity product) {
            return new BrandPrice(
                    product.getBrand().getName()
                    , product.getPrice());
        }

        private static List<BrandPrice> fromList(List<ProductEntity> products) {
            return products.stream()
                    .map(BrandPrice::from)
                    .collect(Collectors.toList());
        }
    }
}
