package com.musinsa.mycoordinator.entity;

import com.musinsa.mycoordinator.domain.ProductCategoryConverter;
import com.musinsa.mycoordinator.domain.code.ProductCategory;
import com.musinsa.mycoordinator.domain.request.ProductRequest;
import com.musinsa.mycoordinator.domain.request.UpdateProductRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "PRODUCT")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE PRODUCT SET deleted = true WHERE id = ?")
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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brandId")
    private BrandEntity brand;

    @Comment("삭제여부")
    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;

    /**
     * BrandRequest to BrandEntity
     */
    public static ProductEntity from(ProductRequest request, BrandEntity brand) {
        ProductEntity product = new ProductEntity();
        product.category = request.getCategory();
        product.price = request.getPrice();
        product.brand = brand;

        return product;
    }

    /**
     * NULL을 제외한 Product 데이터 저장
     */
    public void updateProduct(UpdateProductRequest request, BrandEntity brand) {
        this.brand = brand;

        if (request.getCategory() != null) {
            this.category = request.getCategory();
        }

        if (request.getPrice() > 0) {
            this.price = request.getPrice();
        }
    }
}
