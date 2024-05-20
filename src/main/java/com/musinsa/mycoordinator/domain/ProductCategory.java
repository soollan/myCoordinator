package com.musinsa.mycoordinator.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductCategory {
    TOP("상의"),
    OUTER("아우터"),
    PANTS("바지"),
    SNEAKERS("스니커즈"),
    BAG("가방"),
    HAT("모자"),
    SOCKS("양말"),
    ACCESSORY("액세서리");

    @JsonValue
    private String description;

    public static ProductCategory fromString(String dbData) {
        for (ProductCategory category : ProductCategory.values()) {
            if (category.name().equals(dbData)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown enum type : " + dbData);
    }

    public static ProductCategory fromDescription(String description) {
        for (ProductCategory category : ProductCategory.values()) {
            if (category.getDescription().equals(description)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown description : " + description);
    }

}