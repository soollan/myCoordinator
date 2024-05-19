package com.musinsa.mycoordinator.service;

import com.musinsa.mycoordinator.domain.BrandEntity;
import com.musinsa.mycoordinator.domain.ItemCategory;
import com.musinsa.mycoordinator.domain.LowestBrandMinPrice;
import com.musinsa.mycoordinator.domain.LowestHighestResponse;
import com.musinsa.mycoordinator.domain.LowestResponse;
import com.musinsa.mycoordinator.domain.PriceByCategoryResponse;
import com.musinsa.mycoordinator.domain.Item;
import com.musinsa.mycoordinator.domain.ItemEntity;
import com.musinsa.mycoordinator.exception.NotFoundException;
import com.musinsa.mycoordinator.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final ItemRepository itemRepository;

    /**
     * 카테고리별로 최저가인 브랜드와 가격을 조회한다.
     */
    @Transactional(readOnly = true)
    public PriceByCategoryResponse findLowestPrices() {
        List<ItemEntity> items = itemRepository.findLowestBrandInEachCategory();
        if (items.isEmpty()) {
            throw new NotFoundException("최저가 정보를 찾을 수 없음");
        }

        return new PriceByCategoryResponse(Item.fromList(items));
    }

    /**
     * 모든 카테고리의 상품을 구매시, 최저가격인 브랜드 및 가격 조회
     */
    @Transactional(readOnly = true)
    public LowestResponse findLowestBrand() {
        LowestBrandMinPrice lowestBrand = itemRepository.findByLowestBrandId();
        if (lowestBrand == null || lowestBrand.getBrandId() == null) {
            throw new NotFoundException("최저가 정보를 찾을 수 없음");
        }

        List<ItemEntity> items = itemRepository.findAllByBrandId(lowestBrand.getBrandId());
        if (items.isEmpty()) {
            throw new NotFoundException("브랜드 정보를 찾을 수 없음");
        }

        return new LowestResponse(items.get(0).getBrand().getName(), lowestBrand.getTotalPrice(), items);
    }

    /**
     * 검색할 카테고리의 최저가와 최고가 및 브랜드 조회
     */
    public LowestHighestResponse findLowestAndHighestBrand(ItemCategory category) {
        List<ItemEntity> lowestItems = itemRepository.findAllLowestByCategory(category);
        if (lowestItems.isEmpty()) {
            throw new NotFoundException("최저가 정보를 찾을 수 없음");
        }

        List<ItemEntity> highestItems = itemRepository.findAllHighestByCategory(category);
        if (highestItems.isEmpty()) {
            throw new NotFoundException("최고가 정보를 찾을 수 없음");
        }

        return new LowestHighestResponse(category, lowestItems, highestItems);
    }
}
