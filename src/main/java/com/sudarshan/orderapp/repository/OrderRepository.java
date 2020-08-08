package com.sudarshan.orderapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sudarshan.orderapp.entity.Order;

public interface OrderRepository extends MongoRepository<Order, Integer> {

	@Query("{'customerId': ?0}")
	public List<Order> findByCustomerId(int customerId);
}
