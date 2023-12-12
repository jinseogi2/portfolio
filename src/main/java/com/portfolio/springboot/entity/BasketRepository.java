package com.portfolio.springboot.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
    void deleteByItemName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE CartEntity c SET c.cartItemAmount = :amount WHERE c.itemName = :itemName")
    void updateItemAmount(String itemName, int amount);
}