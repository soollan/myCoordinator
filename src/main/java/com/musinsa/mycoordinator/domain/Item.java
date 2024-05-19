package com.musinsa.mycoordinator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Item {

    private Long id;

    private ItemCategory category;

    private Integer price;

    // TODO: 2024/05/17 change
    private BrandEntity brandId;

    public static Item from(ItemEntity itemEntity) {
        return new Item(itemEntity.getId()
                , itemEntity.getCategory()
                , itemEntity.getPrice()
                , itemEntity.getBrand());
    }

    public static List<Item> fromList(List<ItemEntity> itemEntities) {
        return itemEntities.stream()
                .map(Item::from)
                .collect(Collectors.toList());
    }
}
