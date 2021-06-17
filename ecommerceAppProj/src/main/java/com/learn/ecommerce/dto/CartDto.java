package com.learn.ecommerce.dto;

import java.util.List;

public class CartDto {

	private long cartId;
	private List<CartItemDto> items;
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public List<CartItemDto> getItems() {
		return items;
	}
	public void setItems(List<CartItemDto> items) {
		this.items = items;
	}
	


}
