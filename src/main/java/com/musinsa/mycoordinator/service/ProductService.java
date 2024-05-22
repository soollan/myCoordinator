package com.musinsa.mycoordinator.service;

import com.musinsa.mycoordinator.domain.request.ProductRequest;
import com.musinsa.mycoordinator.domain.request.UpdateProductRequest;
import com.musinsa.mycoordinator.entity.BrandEntity;
import com.musinsa.mycoordinator.entity.ProductEntity;
import com.musinsa.mycoordinator.exception.NotFoundException;
import com.musinsa.mycoordinator.repository.BrandRepository;
import com.musinsa.mycoordinator.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final BrandRepository brandRepository;

    private final ProductRepository productRepository;

    /**
     * 상품 저장
     */
    @Transactional
    public void saveProduct(ProductRequest request) {
        BrandEntity brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new NotFoundException("해당 브랜드가 없습니다."));

        ProductEntity product = ProductEntity.from(request, brand);
        productRepository.save(product);
    }

    /**
     * 상품 변경
     */
    @Transactional
    public void updateProduct(UpdateProductRequest request) {
        ProductEntity product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new NotFoundException("해당 상품이 없습니다."));

        BrandEntity brand;
        if (request.getBrandId() > 0) {
            brand = brandRepository.findById(request.getBrandId())
                    .orElseThrow(() -> new NotFoundException("해당 브랜드가 없습니다."));
        } else {
            brand = product.getBrand();
        }

        product.updateProduct(request, brand);
    }

    /**
     * 상품 삭제
     */
    public void deleteProduct(long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 상품이 없습니다."));

        productRepository.delete(product);
    }
}
