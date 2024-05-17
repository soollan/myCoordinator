package com.musinsa.mycoordinator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemCategory {
    TOP("상의"),
    OUTER("아우터"),
    PANTS("바지"),
    SNEAKERS("스니커즈"),
    BAG("가방"),
    HAT("모자"),
    SOCKS("양말"),
    ACCESSORY("액세서리");

    private String description;

    public static ItemCategory fromString(String dbData) {
        for (ItemCategory category : ItemCategory.values()) {
            if (category.name().equals(dbData)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + dbData);
    }
}
