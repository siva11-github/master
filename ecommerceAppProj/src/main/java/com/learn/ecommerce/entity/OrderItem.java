package com.learn.ecommerce.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private double orderPrice;
	private long cartId;
	private String paymentStatus;
	private int userId; 
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	@ApiModelProperty(hidden=true)
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	@ApiModelProperty(hidden=true)
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;

	}

	public int getUserId() {
		return userId;
	}
	@ApiModelProperty(hidden=true)
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Order_Cart",
	joinColumns = {@JoinColumn(name = "Order_id")},
	inverseJoinColumns = {@JoinColumn(name = "Cart_id")}
			)
	private Cart cart;
	public Cart getCart() {
		return cart;
	}
	@ApiModelProperty(hidden=true)
	public void setCart(Cart cart) {
		this.cart = cart;
		this.cart.setCartId(this.cartId);
	}
	


}


