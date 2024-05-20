package com.musinsa.mycoordinator.repository;

import com.musinsa.mycoordinator.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    boolean existsByName(String name);
}
