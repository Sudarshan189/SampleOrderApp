package com.sudarshan.orderapp.entity;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
@Document(collection="orderscollection")
public class Order {
	@Id
	private int orderId;
	private double total;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private int customerId;
	private String status;
	private Set<Item> items;

	public Order(int orderId, double total, LocalDate date, int customerId, String status, Set<Item> items) {
		super();
		this.orderId = orderId;
		this.total = total;
		this.date = date;
		this.customerId = customerId;
		this.status = status;
		this.items = items;
	}

	public Order() {
		super();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", total=" + total + ", date=" + date + ", customerId=" + customerId
				+ ", status=" + status + ", items=" + items + "]";
	}

}
