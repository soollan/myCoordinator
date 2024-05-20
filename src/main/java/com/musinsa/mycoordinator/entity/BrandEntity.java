package com.musinsa.mycoordinator.entity;

import com.musinsa.mycoordinator.domain.BrandRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "BRAND")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("브랜드명")
    @Column(nullable = false, length = 10)
    private String name;

    /**
     * Convert BrandRequest to BrandEntity
     */
    public static BrandEntity from(BrandRequest request) {
        BrandEntity brand = new BrandEntity();
        brand.name = request.getName();
        return brand;
    }
}
