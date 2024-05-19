package com.musinsa.mycoordinator.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "ITEM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("카테고리")
    @Column(nullable = false, length = 10)
    @Convert(converter = ItemCategoryConverter.class)
    private ItemCategory category;

    @Comment("가격")
    @Column(nullable = false)
    private Integer price;

    @Comment("브랜드")
    @ManyToOne(optional = false)
    @JoinColumn(name = "brandId")
    private BrandEntity brand;
}
