package com.musinsa.mycoordinator.domain;

import com.musinsa.mycoordinator.domain.code.ProductCategory;
import com.musinsa.mycoordinator.entity.ProductEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Product {

    @Schema(description = "상품 아이디", example = "1")
    private Long id;

    @Schema(description = "상품 카테고리", example = "TOP")
    private ProductCategory category;

    @Schema(description = "상품 금액", example = "5000")
    private Integer price;

    @Schema(description = "상품 브랜드", example = "A")
    private Long brandId;

    public static Product from(ProductEntity productEntity) {
        return new Product(productEntity.getId()
                , productEntity.getCategory()
                , productEntity.getPrice()
                , productEntity.getBrand().getId());
    }

    public static List<Product> fromList(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(Product::from)
                .collect(Collectors.toList());
    }
}
