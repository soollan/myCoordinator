package com.musinsa.mycoordinator.service;

import com.musinsa.mycoordinator.domain.request.BrandRequest;
import com.musinsa.mycoordinator.entity.BrandEntity;
import com.musinsa.mycoordinator.exception.BusinessException;
import com.musinsa.mycoordinator.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    /**
     * 브랜드 생성
     */
    @Transactional
    public void createBrand(BrandRequest request) {
        BrandEntity brand = BrandEntity.from(request);

        boolean hasBrandName = brandRepository.existsByName(request.getName());
        if(hasBrandName) {
            throw new BusinessException("이미 등록된 브랜드명입니다.");
        }

        brandRepository.save(brand);
    }
}
