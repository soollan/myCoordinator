package com.musinsa.mycoordinator.domain;


import com.musinsa.mycoordinator.domain.code.ProductCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ProductCategoryConverter implements AttributeConverter<ProductCategory, String> {

    @Override
    public String convertToDatabaseColumn(ProductCategory attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.name();
    }

    @Override
    public ProductCategory convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return ProductCategory.fromString(dbData);
    }
}
