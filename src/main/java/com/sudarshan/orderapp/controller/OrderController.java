package com.sudarshan.orderapp.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudarshan.orderapp.entity.Item;
import com.sudarshan.orderapp.entity.Order;
import com.sudarshan.orderapp.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	private HashMap<Integer, Set<Item>> cartItems;

	public OrderController() {
		cartItems = new HashMap<>();
	}

	@PostMapping("/cart/{customerId}")
	public ResponseEntity<Set<Item>> addToCart(@RequestBody Item item, @PathVariable int customerId) {
		Set<Item> sampleItem = cartItems.get(customerId);
		if (sampleItem == null) {
			sampleItem = new HashSet<>();
			sampleItem.add(item);
			cartItems.put(customerId, sampleItem);
		} else {
			sampleItem.add(item);
			cartItems.put(customerId, sampleItem);
		}
		return new ResponseEntity<Set<Item>>(sampleItem, HttpStatus.OK);
	}

	@GetMapping("/cart/{customerId}")
	public ResponseEntity<Set<Item>> getCartItems(@PathVariable int customerId) {
		Set<Item> sampleItem = cartItems.get(customerId);
		return new ResponseEntity<Set<Item>>(sampleItem, HttpStatus.OK);
	}

	@DeleteMapping("/cart/{customerId}")
	public ResponseEntity<Set<Item>> deleteCartItems(@PathVariable int customerId, @RequestBody Item item) {
		Set<Item> sampleItem = cartItems.get(customerId);
		if (sampleItem != null) {
			System.out.println(sampleItem.remove(item));
			cartItems.put(customerId, sampleItem);
		}
		return new ResponseEntity<Set<Item>>(sampleItem, HttpStatus.OK);
	}

	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		return new ResponseEntity<Order>(orderService.submitOrder(order), HttpStatus.OK);
	}

	@PutMapping("/order/{orderId}")
	public ResponseEntity<Order> updateOrder(@RequestParam String status,
			@PathVariable int orderId) {
		Order order = orderService.getOrder(orderId);
		switch (status) {
		case "CANCELED":
			order.setStatus(status);
			break;
		case "DISPATCHED":
			order.setStatus(status);
			break;
		case "SHIPPED":
			order.setStatus(status);
			break;
		case "PLACED":
			order.setStatus(status);
			break;
		case "DELETED":
			order.setStatus(status);
			break;
			
		default:
			break;
		}
		return new ResponseEntity<Order>(orderService.updateOrder(order), HttpStatus.OK);
	}

	@DeleteMapping("/order/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable int orderId) {
		return new ResponseEntity<Order>(orderService.deleteOrder(orderId), HttpStatus.OK);
	}


	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
		return new ResponseEntity<Order>(orderService.getOrder(orderId), HttpStatus.OK);

	}

	@GetMapping("/customer/orders/{customerId}")
	public ResponseEntity<List<Order>> getOrders(@PathVariable int customerId) {
		return new ResponseEntity<List<Order>>(orderService.getOrderByCustomerId(customerId), HttpStatus.OK);

	}

}
