package com.musinsa.mycoordinator.domain;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToItemCategoryConverter implements Converter<String, ItemCategory> {
    @Override
    public ItemCategory convert(String source) {
        return ItemCategory.fromDescription(source);
    }
}
