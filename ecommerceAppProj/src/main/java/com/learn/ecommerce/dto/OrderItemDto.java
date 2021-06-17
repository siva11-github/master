package com.learn.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderItemDto {

	private Integer orderId;
	private double orderPrice;
	private long cartId;
	private String paymentStatus;
	private CartDto cart;
	
	
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public CartDto getCart() {
		return cart;
	}
	@JsonIgnore
	public void setCart(CartDto cart) {
		this.cart = cart;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	@JsonIgnore
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
}


