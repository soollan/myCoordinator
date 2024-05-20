package com.musinsa.mycoordinator.entity;

import com.musinsa.mycoordinator.domain.ProductCategory;
import com.musinsa.mycoordinator.domain.ProductCategoryConverter;
import com.musinsa.mycoordinator.entity.BrandEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("카테고리")
    @Column(nullable = false, length = 10)
    @Convert(converter = ProductCategoryConverter.class)
    private ProductCategory category;

    @Comment("가격")
    @Column(nullable = false)
    private Integer price;

    @Comment("브랜드")
    @ManyToOne(optional = false)
    @JoinColumn(name = "brandId")
    private BrandEntity brand;
}
