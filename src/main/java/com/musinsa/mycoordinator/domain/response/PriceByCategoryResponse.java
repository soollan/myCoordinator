package com.musinsa.mycoordinator.domain.response;

import com.musinsa.mycoordinator.domain.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class PriceByCategoryResponse {

    @Schema(description = "상품 정보")
    List<Product> products;

    @Schema(description = "총액", example = "34100")
    private int totalPrice;

    public PriceByCategoryResponse(List<Product> products) {
        this.products = products;
        this.totalPrice = products.stream().mapToInt(Product::getPrice).sum();
    }
}
