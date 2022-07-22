package com.devsuperior.Delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.Delivery.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	
}
 