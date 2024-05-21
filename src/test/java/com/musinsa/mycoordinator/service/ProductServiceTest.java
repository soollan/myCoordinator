package com.musinsa.mycoordinator.service;

import com.musinsa.mycoordinator.domain.ProductCategory;
import com.musinsa.mycoordinator.domain.request.ProductRequest;
import com.musinsa.mycoordinator.domain.request.UpdateProductRequest;
import com.musinsa.mycoordinator.exception.NotFoundException;
import com.musinsa.mycoordinator.repository.BrandRepository;
import com.musinsa.mycoordinator.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.willThrow;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("상품 저장시, 브랜드명이 존재하지 않으면 404 에러가 발생한다.")
    void noBrand() {
        //given
        ProductRequest request = new ProductRequest();
        request.setBrandId(99L);
        request.setCategory(ProductCategory.BAG);
        request.setPrice(100);

        willThrow(NotFoundException.class).given(brandRepository).findById(request.getBrandId());

        //when & then
        assertThrows(NotFoundException.class, () -> productService.saveProduct(request));
    }

    @Test
    @DisplayName("상품아이디가 디비에 존재하지 않으면 404 에러가 발생한다.")
    void noProductForUpdate() {
        UpdateProductRequest updateRequest = new UpdateProductRequest();
        updateRequest.setPrice(100);
        updateRequest.setProductId(99L);
        updateRequest.setCategory(ProductCategory.BAG);
        updateRequest.setBrandId(99L);

        willThrow(NotFoundException.class).given(productRepository).findById(updateRequest.getProductId());

        assertThrows(NotFoundException.class, () -> productService.updateProduct(updateRequest));
    }
}