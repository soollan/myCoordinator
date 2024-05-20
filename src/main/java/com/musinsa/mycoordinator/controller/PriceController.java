package com.musinsa.mycoordinator.controller;

import com.musinsa.mycoordinator.domain.ProductCategory;
import com.musinsa.mycoordinator.domain.LowestHighestResponse;
import com.musinsa.mycoordinator.domain.LowestResponse;
import com.musinsa.mycoordinator.domain.PriceByCategoryResponse;
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

    // TODO: 2024/05/19 응답 데이터 중 가격은 콤마처리해야될ㄷㅅ
    // TODO: 2024/05/20 응답 한글로

    @GetMapping("/lowest")
    @Operation(summary = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회")
    public PriceByCategoryResponse findPriceByCategory() {
        return priceService.findLowestPrices();
    }

    @GetMapping("/lowest/combo")
    @Operation(summary = "모든 카테고리 구매시, 최저가격 브랜드 및 정보 조회")
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
