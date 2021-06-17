package com.learn.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.ecommerce.entity.CartItem;

@Repository
public interface CartItemsRepository  extends JpaRepository<CartItem, Integer>{

}
