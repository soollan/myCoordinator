package com.musinsa.mycoordinator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LowestHighestResponse {

    ItemCategory category;

    List<BrandPrice> lowest;

    List<BrandPrice> highest;

    public LowestHighestResponse(ItemCategory category, List<ItemEntity> lowest, List<ItemEntity> highest) {
        this.category = category;
        this.lowest = BrandPrice.fromList(lowest);
        this.highest = BrandPrice.fromList(highest);
    }

    @Getter
    @AllArgsConstructor
    private static class BrandPrice {
        String brand;
        Integer price;

        private static BrandPrice from(ItemEntity item) {
            return new BrandPrice(
                    item.getBrand().getName()
                    , item.getPrice());
        }

        private static List<BrandPrice> fromList(List<ItemEntity> items) {
            return items.stream()
                    .map(BrandPrice::from)
                    .collect(Collectors.toList());
        }
    }
}
