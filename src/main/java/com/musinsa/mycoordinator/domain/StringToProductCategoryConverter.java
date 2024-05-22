package com.musinsa.mycoordinator.domain;

import com.musinsa.mycoordinator.domain.code.ProductCategory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToProductCategoryConverter implements Converter<String, ProductCategory> {
    @Override
    public ProductCategory convert(String source) {
        return ProductCategory.fromDescription(source);
    }
}
