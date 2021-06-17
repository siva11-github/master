package com.learn.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.ecommerce.entity.OrderItem;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem, Integer>
{
	
}
