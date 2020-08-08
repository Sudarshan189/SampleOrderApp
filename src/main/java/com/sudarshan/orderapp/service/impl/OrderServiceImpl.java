package com.sudarshan.orderapp.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudarshan.orderapp.entity.Item;
import com.sudarshan.orderapp.entity.Order;
import com.sudarshan.orderapp.repository.OrderRepository;
import com.sudarshan.orderapp.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order submitOrder(Order order) {
		order.setStatus("Processing");
		Set<Item> cartItems = order.getItems();
		double totalPrice = 0;
		for (Item item : cartItems) {
			totalPrice += item.getPrice();
		}
		order.setTotal(totalPrice);
		order.setDate(LocalDate.now());
		return orderRepository.save(order);
	}

	@Override
	public Order updateOrder(Order order) {

		return orderRepository.save(order);

	}

	@Override
	public Order cancelOrder(int orderId) {
		Optional<Order> optional = orderRepository.findById(orderId);
		if (optional.isPresent()) {
			optional.get().setStatus("Cancelled");
			return orderRepository.save(optional.get());
		}
		return null;
	}

	@Override
	public Order deleteOrder(int orderId) {
		Optional<Order> optional = orderRepository.findById(orderId);
		if (optional.isPresent()) {
			optional.get().setStatus("Deleted");
			return orderRepository.save(optional.get());
		}
		return null;
	}

	@Override
	public Order getOrder(int orderId) {
		Optional<Order> optional = orderRepository.findById(orderId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Order> getOrderByCustomerId(int customerId) {
		return orderRepository.findByCustomerId(customerId);
	}

	@Override
	public List<Order> getOrderByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
