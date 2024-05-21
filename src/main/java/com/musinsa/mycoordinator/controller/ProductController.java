package com.musinsa.mycoordinator.controller;

import com.musinsa.mycoordinator.domain.request.ProductRequest;
import com.musinsa.mycoordinator.domain.request.UpdateProductRequest;
import com.musinsa.mycoordinator.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "상품 관련")
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "상품 등록")
    public void createProduct(@RequestBody @Valid ProductRequest request) {
        productService.saveProduct(request);
    }

    @PutMapping
    @Operation(summary = "상품 변경")
    public void updateProduct(@RequestBody @Valid UpdateProductRequest request) {
        productService.updateProduct(request);
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "상품 삭제")
    public void deleteProduct(
            @PathVariable
            @Parameter(description = "상품 ID")
                    long productId) {
        productService.deleteProduct(productId);
    }

}
