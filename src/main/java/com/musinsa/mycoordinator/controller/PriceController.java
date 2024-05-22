package com.musinsa.mycoordinator.controller;

import com.musinsa.mycoordinator.domain.code.ProductCategory;
import com.musinsa.mycoordinator.domain.response.LowestHighestResponse;
import com.musinsa.mycoordinator.domain.response.LowestResponse;
import com.musinsa.mycoordinator.domain.response.PriceByCategoryResponse;
import com.musinsa.mycoordinator.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "가격 관련")
@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    // TODO: 2024/05/19 응답 데이터 중 가격은 콤마처리

    @GetMapping("/lowest")
    @Operation(summary = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회")
    public PriceByCategoryResponse findPriceByCategory() {
        return priceService.findLowestPrices();
    }

    @GetMapping("/lowest/combo")
    @Operation(summary = "단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회")
    public LowestResponse findBrand() {
        return priceService.findLowestBrand();
    }

    @GetMapping("/lowest/highest/{category}")
    @Operation(summary = "검색할 카테고리의 최저가와 최고가 및 브랜드 조회")
    public LowestHighestResponse findBrand(
            @PathVariable
            @Parameter(description = "카테고리명")
                    ProductCategory category) {
        return priceService.findLowestAndHighestBrand(category);
    }
}
