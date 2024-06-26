package com.musinsa.mycoordinator.service;

import com.musinsa.mycoordinator.entity.ProductEntity;
import com.musinsa.mycoordinator.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PriceServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("카테고리별로 최저가인 브랜드와 가격은 반드시 존재한다.")
    void findLowestPrices() {
        // given, when
        List<ProductEntity> products = productRepository.findLowestBrandInEachCategory();

        // then
        assertNotNull(products);
    }
}