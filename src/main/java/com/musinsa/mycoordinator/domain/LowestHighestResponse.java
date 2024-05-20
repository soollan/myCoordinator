package com.musinsa.mycoordinator.domain;

import com.musinsa.mycoordinator.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LowestHighestResponse {

    ProductCategory category;

    List<BrandPrice> lowest;

    List<BrandPrice> highest;

    public LowestHighestResponse(ProductCategory category, List<ProductEntity> lowest, List<ProductEntity> highest) {
        this.category = category;
        this.lowest = BrandPrice.fromList(lowest);
        this.highest = BrandPrice.fromList(highest);
    }

    @Getter
    @AllArgsConstructor
    private static class BrandPrice {
        String brand;
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
