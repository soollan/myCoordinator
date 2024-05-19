package com.musinsa.mycoordinator.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ItemCategoryConverter implements AttributeConverter<ItemCategory, String> {

    @Override
    public String convertToDatabaseColumn(ItemCategory attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.name();
    }

    @Override
    public ItemCategory convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return ItemCategory.fromString(dbData);
    }
}
