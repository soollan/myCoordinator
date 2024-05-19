package com.musinsa.mycoordinator.repository;

import com.musinsa.mycoordinator.domain.Item;
import com.musinsa.mycoordinator.domain.ItemCategory;
import com.musinsa.mycoordinator.domain.ItemEntity;
import com.musinsa.mycoordinator.domain.LowestBrandMinPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Query("SELECT item1 FROM ItemEntity item1 WHERE item1.price = (SELECT MIN(item2.price) FROM ItemEntity item2 WHERE item2.category = item1.category) order by item1.category")
    List<ItemEntity> findLowestBrandInEachCategory();

    @Query(value = "SELECT brand_Id, SUM(price) as total_price FROM item GROUP BY brand_Id ORDER BY total_price ASC limit 1", nativeQuery = true)
    LowestBrandMinPrice findByLowestBrandId();

    List<ItemEntity> findAllByBrandId(Long brandId);

    @Query("SELECT item1 FROM ItemEntity item1 WHERE item1.price = (SELECT MIN(item2.price) FROM ItemEntity item2 WHERE item2.category = ?1)")
    List<ItemEntity> findAllLowestByCategory(ItemCategory category);

    @Query("SELECT item1 FROM ItemEntity item1 WHERE item1.price = (SELECT MAX(item2.price) FROM ItemEntity item2 WHERE item2.category = ?1)")
    List<ItemEntity> findAllHighestByCategory(ItemCategory category);
}
