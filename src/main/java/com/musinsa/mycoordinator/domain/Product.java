package com.musinsa.mycoordinator.domain;

import com.musinsa.mycoordinator.entity.BrandEntity;
import com.musinsa.mycoordinator.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Product {

    private Long id;

    private ProductCategory category;

    private Integer price;

    // TODO: 2024/05/17 change
    private BrandEntity brandId;

    public static Product from(ProductEntity productEntity) {
        return new Product(productEntity.getId()
                , productEntity.getCategory()
                , productEntity.getPrice()
                , productEntity.getBrand());
    }

    public static List<Product> fromList(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(Product::from)
                .collect(Collectors.toList());
    }
}
