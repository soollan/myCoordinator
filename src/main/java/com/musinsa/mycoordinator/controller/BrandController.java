package com.musinsa.mycoordinator.controller;

import com.musinsa.mycoordinator.domain.request.BrandRequest;
import com.musinsa.mycoordinator.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "브랜드 관련")
@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    @Operation(summary = "브랜드 등록")
    public void createBrand(@RequestBody @Valid BrandRequest request) {
        brandService.createBrand(request);
    }
}
