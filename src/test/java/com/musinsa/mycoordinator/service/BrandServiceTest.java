package com.musinsa.mycoordinator.service;

import com.musinsa.mycoordinator.domain.BrandRequest;
import com.musinsa.mycoordinator.exception.BusinessException;
import com.musinsa.mycoordinator.repository.BrandRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willThrow;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @InjectMocks
    private BrandService brandService;

    @Mock
    private BrandRepository repository;

    @Test
    @DisplayName("브랜드 저장시, 이미 브랜드명이 존재하면 400 에러가 발생한다.")
    void fail() {
        //given
        BrandRequest request = new BrandRequest();
        request.setName("A");

        willThrow(BusinessException.class).given(repository).existsByName(request.getName());

        //when & then
        assertThrows(BusinessException.class, () -> brandService.createBrand(request));
    }
}