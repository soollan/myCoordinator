package com.musinsa.mycoordinator.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class PriceByCategoryResponse {

    @Schema(description = "상품 정보")
    List<Item> items;

    @Schema(description = "총액", example = "34100")
    private int totalPrice;

    public PriceByCategoryResponse(List<Item> items) {
        this.items = items;
        this.totalPrice = items.stream().mapToInt(Item::getPrice).sum();
    }
}
