package com.musinsa.mycoordinator.service;

import com.musinsa.mycoordinator.domain.ItemCategory;
import com.musinsa.mycoordinator.domain.ItemEntity;
import com.musinsa.mycoordinator.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.notification.Failure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PriceServiceTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("카테고리별로 최저가인 브랜드를 가져온다.")
    void findLowestPrices() {
        // given, when
        List<ItemEntity> items = itemRepository.findLowestBrandInEachCategory();

        // then
        assertThat(items).size().isEqualTo(9);
    }

    @Test
    @DisplayName("모든 카테고리 구매시, 최저가격 브랜드 및 정보 조회")
    void findLowestBrand() {

    }
}