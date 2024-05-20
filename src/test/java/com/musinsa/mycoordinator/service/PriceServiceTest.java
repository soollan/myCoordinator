package com.musinsa.mycoordinator.service;

import com.musinsa.mycoordinator.entity.ProductEntity;
import com.musinsa.mycoordinator.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PriceServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("카테고리별로 최저가인 브랜드를 가져온다.")
    void findLowestPrices() {
        // given, when
        List<ProductEntity> products = productRepository.findLowestBrandInEachCategory();

        // then
        assertThat(products).size().isEqualTo(9);
    }

    @Test
    @DisplayName("모든 카테고리의 최저가격 브랜드 및 정보 조회")
    void findLowestBrand() {

    }
}