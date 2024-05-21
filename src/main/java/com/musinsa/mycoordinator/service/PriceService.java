package com.musinsa.mycoordinator.service;

import com.musinsa.mycoordinator.domain.Product;
import com.musinsa.mycoordinator.domain.ProductCategory;
import com.musinsa.mycoordinator.entity.ProductEntity;
import com.musinsa.mycoordinator.domain.LowestBrandMinPrice;
import com.musinsa.mycoordinator.domain.response.LowestHighestResponse;
import com.musinsa.mycoordinator.domain.response.LowestResponse;
import com.musinsa.mycoordinator.domain.response.PriceByCategoryResponse;
import com.musinsa.mycoordinator.exception.NotFoundException;
import com.musinsa.mycoordinator.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final ProductRepository productRepository;

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
     * 모든 카테고리의 상품을 구매시, 최저가격인 브랜드 및 가격 조회
     */
    @Transactional(readOnly = true)
    public LowestResponse findLowestBrand() {
        LowestBrandMinPrice lowestBrand = productRepository.findByLowestBrandId();
        if (lowestBrand == null || lowestBrand.getBrandId() == null) {
            throw new NotFoundException("최저가 정보를 찾을 수 없음");
        }

        List<ProductEntity> products = productRepository.findAllByBrandId(lowestBrand.getBrandId());
        if (products.isEmpty()) {
            throw new NotFoundException("브랜드 정보를 찾을 수 없음");
        }

        return new LowestResponse(products.get(0).getBrand().getName(), lowestBrand.getTotalPrice(), products);
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
}
