package com.musinsa.mycoordinator.service;

import com.musinsa.mycoordinator.domain.Product;
import com.musinsa.mycoordinator.domain.code.ProductCategory;
import com.musinsa.mycoordinator.domain.response.BrandResponse;
import com.musinsa.mycoordinator.domain.response.LowestHighestResponse;
import com.musinsa.mycoordinator.domain.response.LowestResponse;
import com.musinsa.mycoordinator.domain.response.PriceByCategoryResponse;
import com.musinsa.mycoordinator.entity.BrandEntity;
import com.musinsa.mycoordinator.entity.ProductEntity;
import com.musinsa.mycoordinator.exception.NotFoundException;
import com.musinsa.mycoordinator.repository.BrandRepository;
import com.musinsa.mycoordinator.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    /**
     * 카테고리별로 최저가인 브랜드와 가격을 조회한다.
     */
    @Transactional(readOnly = true)
    public PriceByCategoryResponse findLowestPrices() {
        List<ProductEntity> products = productRepository.findLowestBrandInEachCategory();
        if (products.isEmpty()) {
            throw new NotFoundException("최저가 정보를 찾을 수 없음");
        }

        return new PriceByCategoryResponse(Product.fromList(products));
    }

    /**
     * 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회
     */
    @Transactional(readOnly = true)
    public LowestResponse findLowestBrand() {
        List<BrandEntity> brandList = brandRepository.findAll();

        BrandResponse response = brandList.stream()
                .map(brand -> getMinPriceSumByCategory(brand))
                .min(Comparator.comparingInt(BrandResponse::getTotalPrice))
                .orElseThrow(() -> new NotFoundException("최저가 정보를 찾을 수 없음"));

        return new LowestResponse(response);
    }

    /**
     * 검색할 카테고리의 최저가와 최고가 및 브랜드 조회
     */
    public LowestHighestResponse findLowestAndHighestBrand(ProductCategory category) {
        List<ProductEntity> lowestProducts = productRepository.findAllLowestByCategory(category);
        if (lowestProducts.isEmpty()) {
            throw new NotFoundException("최저가 정보를 찾을 수 없음");
        }

        List<ProductEntity> highestProducts = productRepository.findAllHighestByCategory(category);
        if (highestProducts.isEmpty()) {
            throw new NotFoundException("최고가 정보를 찾을 수 없음");
        }

        return new LowestHighestResponse(category, lowestProducts, highestProducts);
    }

    /**
     * 하나의 브랜드에 모든 카테고리의 최저가들의 합을 조회
     */
    private BrandResponse getMinPriceSumByCategory(BrandEntity brand) {
        List<ProductEntity> products = productRepository.findAllByBrandId(brand.getId());

        int productSum = products.stream()
                .collect(Collectors.groupingBy(
                        ProductEntity::getCategory,
                        Collectors.minBy(Comparator.comparingInt(ProductEntity::getPrice))
                ))
                .values()
                .stream()
                .mapToInt(product -> product.get().getPrice())
                .sum();

        return new BrandResponse(brand.getName(), productSum, products);
    }
}
