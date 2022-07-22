package com.devsuperior.Delivery.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.Delivery.dto.OrderDTO;
import com.devsuperior.Delivery.dto.ProductDTO;
import com.devsuperior.Delivery.entities.Order;
import com.devsuperior.Delivery.entities.OrderStatus;
import com.devsuperior.Delivery.entities.Product;
import com.devsuperior.Delivery.repositories.OrderRepository;
import com.devsuperior.Delivery.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream().map(p -> new OrderDTO(p)).collect(Collectors.toList());

	}
  
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), 
				Instant.now(), OrderStatus.PENDING);
		for (ProductDTO p : dto.getProduct()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = repository.save(order);
		return new OrderDTO(order);
	}
}
