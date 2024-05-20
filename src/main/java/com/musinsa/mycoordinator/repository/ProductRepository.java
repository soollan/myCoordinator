package com.musinsa.mycoordinator.repository;

import com.musinsa.mycoordinator.domain.LowestBrandMinPrice;
import com.musinsa.mycoordinator.domain.ProductCategory;
import com.musinsa.mycoordinator.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT product1 FROM ProductEntity product1 WHERE product1.price = (SELECT MIN(product2.price) FROM ProductEntity product2 WHERE product2.category = product1.category) order by product1.category")
    List<ProductEntity> findLowestBrandInEachCategory();

    @Query(value = "SELECT brand_Id, SUM(price) as total_price FROM product GROUP BY brand_Id ORDER BY total_price ASC limit 1", nativeQuery = true)
    LowestBrandMinPrice findByLowestBrandId();

    List<ProductEntity> findAllByBrandId(Long brandId);

    @Query("SELECT product1 FROM ProductEntity product1 WHERE product1.price = (SELECT MIN(product2.price) FROM ProductEntity product2 WHERE product2.category = ?1)")
    List<ProductEntity> findAllLowestByCategory(ProductCategory category);

    @Query("SELECT product1 FROM ProductEntity product1 WHERE product1.price = (SELECT MAX(product2.price) FROM ProductEntity product2 WHERE product2.category = ?1)")
    List<ProductEntity> findAllHighestByCategory(ProductCategory category);
}
